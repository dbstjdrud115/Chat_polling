package com.ll.chat2412.Chating.dto;

/*@Getter
@Setter
public class MessagesRequest {

    private Long fromId;

}*/

//record는 @getter, @setter를 대체할 수는 있지만,
//불변 객체라, 한 번 세팅된 값을 변경할 수 없다.
//이 DTO는 메시지를 요청하는 Req인데, 한 번 호출할때마다, fromId값이 바뀌고,
//fromId는 변경될 일이 없기에, record를 사용하는 듯 하나.. 엄밀히 구분하지 못하면
//사용자에게 혼선이 생길 우려가 있어보인다. 결론 : 그냥 lombok이 좋지 않을까?
public record MessagesRequest(Long fromId) {}