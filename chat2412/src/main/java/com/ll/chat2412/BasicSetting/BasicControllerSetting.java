package com.ll.chat2412.BasicSetting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sample")
public class BasicControllerSetting {

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
