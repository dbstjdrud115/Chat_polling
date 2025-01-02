package com.ll.chat2412.BasicSetting;

import com.ll.chat2412.Chating.ChatMessage;
import com.ll.chat2412.Chating.RsData;
import com.ll.chat2412.Chating.dto.MessagesRequest;
import com.ll.chat2412.Chating.dto.MessagesResponse;
import com.ll.chat2412.Chating.dto.writeMessageRequest;
import com.ll.chat2412.Chating.dto.writeMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sample")
public class BasicControllerSetting {

    //Get처리
    //Body에 데이터 담아 회신
    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages(MessagesRequest messagesRequest) {
        return new RsData("200", "OK", new MessagesResponse(null, 0));
    }

    //Post처리.
    //Body에 데이터 담아 회신
    //@RequestBody를 사용하여, 화면에서 보낸 body를 읽어, 매개변수 이름이 같은 경우 자동 매핑
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<writeMessageResponse>writeMessage(@RequestBody writeMessageRequest req){
        ChatMessage ch = new ChatMessage(req.getAuthorName(), req.getContent());
        return new RsData("200", "OK", new writeMessageResponse(ch));
    }


    //화면이동 필요시
    @GetMapping("/room")
    public String room() {
        return "chat/room";
    }

    @GetMapping("/roomForSSE")
    public String roomForSSE() {
        return "chat/roomForSSE";
    }

    @GetMapping("/roomForWebSocket")
    public String roomForWebSocket() {
        return "chat/roomForWebSocket";
    }
}
