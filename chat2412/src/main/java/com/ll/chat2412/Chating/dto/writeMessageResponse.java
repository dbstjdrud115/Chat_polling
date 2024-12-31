package com.ll.chat2412.Chating.dto;

import com.ll.chat2412.Chating.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class writeMessageResponse {
    private ChatMessage chatMessage;
}
