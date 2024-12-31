package com.li.chat;

import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @GetMapping("/room")
    @ResponseBody
    public String room() {
        return "room";
    }

    public record MessagesRequest(Long fromId, Long toId) {}

    public record ChatWriteResponse(Long id){}

    /*@PostMapping("/message")
    @ResponseBody
    public String message() {

        List<ChatMessage> chatMessages = new ArrayList<>();

        ChatMessage message = new ChatMessage("test", "content");

        chatMessages.add(message);

        return new RsData(
                "200",
                "메시지가 성공적으로 전송되었습니다.",
                new ChatWriteResponse(1);
        );
    }

    @GetMapping("/message")
    @ResponseBody
    public RsData<List<ChatMessage>> messages(){

        //오버로딩된 message 클래스를 사용
        ChatMessage message = new ChatMessage("test", "content");

        return new RsData<>(
                "200",
                "메시지가 성공적으로 전송되었습니다.",
                 ChatMessage("200", "성공", message)
        );
    }*/

    @PostMapping("/messageTest")
    public void messageTest() {
        System.out.println("메시지 테스트");
    }


}
