package com.ll.chat2412.Chating.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class writeMessageRequest {
    private String authorName;
    private String content;
}
