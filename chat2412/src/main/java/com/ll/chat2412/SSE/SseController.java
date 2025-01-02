package com.ll.chat2412.SSE;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Controller
@RequestMapping("/sse")
@RequiredArgsConstructor
public class SseController {

    //Sse연결 관리 클래스인 Emitter를 기반으로, 복수의 emitter연결을 위해 emitters를 만들었다.
    private final SseEmitters sseEmitters;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {

        //화면 오픈시, sse통신api를 호출하고, 연결시에 connet 정보를 보내는 단건적인 처리로직이다.
        //복수의 emitter를 관리하기 위해 sseEmitters에 emitter를 추가한다.
        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);

        try {
            emitter.send(SseEmitter.event()
                    .name("connect")    // 이벤트 이름을 "connect"로 지정
                    .data("connected!")); // 전송할 데이터
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(emitter);
    }



}
