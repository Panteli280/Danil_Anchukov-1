package com.company;

public class Main {
    
    public static void main(String[] args) {
        Student student = new Student("Anchukov", "Danil");
        student = fillRandomMark(student);

        System.out.println(student + "\n");

        for (int semester = 1; semester <= Field.COUNT_MAX_SEMESTER; semester++) {
            System.out.printf("Avg to semester [%4s] equal: %.2f;\n",
                    student.getSemester(semester), student.countToSemester(semester));
        }

        System.out.printf("\nFull avg equal: %.2f;\n", student.countFull());
    }

    private static Student fillRandomMark(Student student) {
        if (student != null) {
            for (int semester = 1; semester <= Field.COUNT_MAX_SEMESTER; semester++) {
                for (String subject : Field.SUBJECTS) {
                    student.addMark(subject, semester, GetRandomMark(Field.MIN_VALUE_SCORE, Field.MAX_VALUE_SCORE));
                }
            }
        }
        return student;
    }

    private static int GetRandomMark(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}