package study;


import study.genericSampleClass.GradeCard;

import java.util.*;


public class genericImplement {

    public static void main(String[] args) {

        //Generic을 사용하려면
        //1. 제네릭으로 사용할 DTO 클래스에 제네릭 타입을 선언한다.
        //2. DTO 클래스를 사용할때 제네릭으로 설정했던 필드의 데이터타입을 명시한다.

        //1. 제네릭타입을 사용하는 genericSample.GradeCard 클래스를 만들었다.
        //2. GradeCard 클래스를 사용할때, 제네릭 타입의 데이터타입을 명시했다.

        //제네릭이 문자열인 경우
        GradeCard<Integer, String> mathCard = new GradeCard<>("김철수", 95, "문자열");
        mathCard.displayInfo();

        //제네릭이 배열인 경우
        List<String> listData = new ArrayList();
        listData.add("1");
        listData.add("2");
        GradeCard<String, List<String>> englishCard = new GradeCard<>("박영희", "A+", listData);
        englishCard.displayInfo();

        //제네릭이 key-value형태인 경우
        Map<String, String> mapData = new HashMap<>();
        mapData.put("key1", "value1");
        mapData.put("key2", "value2");
        GradeCard<Double, Map> scienceCard = new GradeCard<>("이지민", 88.5, mapData);
        scienceCard.displayInfo();

        mathCard.setGrade(98);
        mathCard.displayInfo();

        listData.add("3");
        englishCard.setGenericTestData(listData);
        englishCard.displayInfo();


    }
}
