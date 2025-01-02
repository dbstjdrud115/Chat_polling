package com.ll.chat2412.SSE;


import com.ll.chat2412.Ut;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j
public class SseEmitters {

    // Thread-safe한 List를 사용하여 다중 클라이언트의 SSE 연결들을 관리
    //  https://tecoble.techcourse.co.kr/post/2022-10-11-server-sent-events/
    //  위 주소에 설명이 있긴 한데, 타 thread에서 실행되니까 안전성을 주의해야한다는
    // 그런 개념은 어떻게 익힌걸까? 왜 그냥 ArrayList가 아닌, CopyOnWriteArrayList를 사용했을까?
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // 새로운 SSE 연결을 추가하고 관련 콜백을 설정하는 메서드
    public SseEmitter add(SseEmitter emitter) {

        this.emitters.add(emitter);
        // 클라이언트와의 연결이 완료되면 컬렉션에서 제거하는 콜백
        emitter.onCompletion(() -> { //연결 완료시 emitter제거. 왜 제거 할까?
            this.emitters.remove(emitter);
        });

        // 연결이 타임아웃되면 완료 처리하는 콜백
        emitter.onTimeout(() -> {emitter.complete();});
        return emitter;
    }

    // 데이터 없이 이벤트 이름만으로 알림을 보내는 간편 메서드

    //1. 왜 LinkedHashMap을 선택할 수 있었을까?
    //2.
    public void noti(String eventName) {
        noti(eventName, Ut.mapOf());
    }

    //Map<String, Integer> map = Ut.mapOf("key1", 1, "key2", 2);

    public void noti(String eventName, Map<String, Object> data) {
        //emitter 데이터가 배열로 담겨있는 emitters에서 emitter를 하나씩 꺼내어,
        //각 emitter마다 이벤트를 전송한다.
        emitters.forEach(emitter -> {
            try {
                emitter.send(
                        SseEmitter.event()
                                .name(eventName)    // 이벤트 이름 설정
                                .data(data)         // 전송할 데이터 설정
                );
            } catch (ClientAbortException e) {
                // 클라이언트가 연결을 강제로 종료한 경우 무시
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
