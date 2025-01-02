package com.ll.chat2412;

import java.util.LinkedHashMap;
import java.util.Map;


public class Ut {
    // 키-값 쌍을 가변 인자로 받아 Map을 생성하는 유틸리티 메서드
    // 사용 예: mapOf("name", "홍길동", "age", 20)


    /*보통 내가 지금까지 봐왔던 Map<String, Integer> map = new HashMap<>();
      위 같은 방식은 Map<K,V>에 맞는다. 이런 방식을 "제네릭 클래스"라고 하는데,
      이 클래스를 사용하는곳에서 제네릭의 데이터 타입을 정의하면 된다.

      Map의 제네릭을 앞에 붙히면 제네릭 메서드라고 하는데, 제네릭 메서드는
      메서드 내부에서 사용할 제네릭 타입을 정의할떄 사용된다.
      다만.. 아직까지는 메서드 내부에서 별도로 사용하기 위한 제네릭이 사용된다는게
      익숙하지 않다.

      Object... args 는 매개변수의 숫자, 데이터타입 상관없이 모두 배열형태로
      수집할 수 있는 가변인자 라고 불리는 존재이다.
      Number... number처럼 수신하려는 타입을 구체적으로 정의하는 규격도 있다고 한다.
    */
    public static <K, V> Map<K, V> mapOf(Object... args) {
        Map<K, V> map = new LinkedHashMap<>();
        int size = args.length / 2;

        // 인자를 2개씩 묶어서 키-값 쌍으로 처리
        for (int i = 0; i < size; i++) {
            int keyIndex = i * 2;
            int valueIndex = keyIndex + 1;
            K key = (K) args[keyIndex];     // 짝수 인덱스는 키로 사용
            V value = (V) args[valueIndex];  // 홀수 인덱스는 값으로 사용
            map.put(key, value);
        }
        return map;
    }
}