package study;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class genericSampleClass {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class GradeCard<T, GenericTest> {
        private String studentName;
        private T grade;
        private GenericTest genericTestData;

        public void displayInfo() {
            System.out.println("학생 이름: " + studentName);
            System.out.println("성적: " + grade);
            System.out.println("제네릭 테스트 데이터: " + genericTestData);
        }
    }
}
