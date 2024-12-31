package com.ll.chat2412.Chating;

import com.ll.chat2412.Chating.dto.MessagesRequest;
import com.ll.chat2412.Chating.dto.MessagesResponse;
import com.ll.chat2412.Chating.dto.writeMessageRequest;
import com.ll.chat2412.Chating.dto.writeMessageResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@NoArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    //과거 메세지 이력까지 꺼내와야하기 때문에 배열구조를 띄고있으며 게시물id, 유저명,입력내용,날짜가 포함되어있다.
    private List<ChatMessage> chatMessages = new ArrayList<>();


    //메세지 입력. Request에서 내용물을 꺼낸 뒤 입력하고, 화면에 회신해줘야한다.
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeMessageResponse>writeMessage(@RequestBody writeMessageRequest req){

        //매개변수 2개의 ChatMessage는 새 id로 데이터를 생성한다.
        ChatMessage ch = new ChatMessage(req.getAuthorName(), req.getContent());
        chatMessages.add(ch);

        //데이터 생성 후 화면에 회신한다.
        return new RsData("200", "OK", new writeMessageResponse(ch));
    }

    //MessagesRequest는 record를 쓰는데, 화면에서 넘겨줄 id가 불변이기에
    //사용한것이다. dto 내부에 가변적인 요소가 있다면 그냥 lombok을 사용하는게 좋아보인다.
    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages(MessagesRequest messagesRequest) {

        List<ChatMessage> messages = chatMessages;
        //화면에서 요청 id값을 넘겨준경우
        if (messagesRequest.fromId() != null) {

            //기존 저장된 메세지 중에서, 화면에서 넘긴 id와 일치하는 대상이 있는지 찾는다.
            //해당하는 대상이 있으면 그 i번째 대상 이후의 메시지만 보낸다.
            //만약 모든 메시지값을 다 보내주게 된다면 큰 부하가 발생하기에,
            //채팅앱같은 경우는 이처럼 특정 id단위로 끊어서 데이터를 보내준다.
            /*int index = -1;
            for (int i = 0; i < messages.size(); i++) {
                if (messages.get(i).getId() == messagesRequest.fromId()) {
                    index = i;
                    break;
                }
            }*/

            //스트림은 반복문의 대체제라고 한다.
            //.range는 반복의 범위를, filter는 걸러줄 조건의 대상을 정한다.
            //.findFirst는 그 조건에 걸린 첫 번째 대상을 반환하고, 없는 경우엔 -1을 반환한다.
            int index = IntStream.range(0, messages.size())
                    .filter(i -> chatMessages.get(i).getId() == messagesRequest.fromId())
                    .findFirst()
                    .orElse(-1);

            //화면의 요청 id값이 존재하는 경우(-1이 아닌경우)
            if (index != -1) {
                messages = messages.subList(index + 1, messages.size());
            }

        }

        return new RsData("200", "메세지 가져오기 성공", new MessagesResponse(messages, chatMessages.size()));
    }

    @GetMapping("/room")
    public String room() {
        return "chat/room";
    }
}
