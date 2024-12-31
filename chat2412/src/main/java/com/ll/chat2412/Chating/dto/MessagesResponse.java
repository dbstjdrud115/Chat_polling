package com.ll.chat2412.Chating.dto;

import com.ll.chat2412.Chating.ChatMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class MessagesResponse {
    private List<ChatMessage> chatMessages;
    private int totCnt;
}
