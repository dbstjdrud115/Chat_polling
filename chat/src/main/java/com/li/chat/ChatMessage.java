package com.li.chat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {

    private long id;
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    //AllArgsConstructor를 사용하면 생성자가 생기지만, rombok에서 생성자를 만드는 프로세스는,
    //자바 컴파일 이후에 이루어진다. 자바컴파일시에는 this = ChatMessage의 생성자가 만들어지지 않았기에
    //별도로 기본생성자를 작성했을경우, 해당 에러가 해소된다.
    //public ChatMessage(long i, LocalDateTime now, String authorName, String content) {}

    //오버로딩된 클래스
    public ChatMessage(String authorName, String content) {
        this(1, LocalDateTime.now(), authorName, content);
    }
}


