package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Student {
    private static final String[] SEMESTERS = {"I", "II", "III", "IV"};

    private String Name;
    private String Surname;
    //мап с ключом в виде номера семестра и содержащий в себе ещё один мап с ключом в виде предмета и значением оценки
    private Map<String, Map<String, Integer>> tree;

    Student(String Name, String Surname) {
        this.Name = enterName(Name);
        this.Surname = enterName(Surname);
        this.tree = new TreeMap();
    }

    public void addMark(String subject, int semester, int mark) {
        if (subject != null && semester > 0 && semester <= SEMESTERS.length && mark > 0) {
            String keyword = SEMESTERS[semester - 1];
            if (!this.tree.containsKey(keyword)) {
                this.tree.put(keyword, new HashMap<>());
            }
            this.tree.get(keyword).put(subject.toUpperCase(), mark);
        }
    }

    public double countToSemester(int semester) {
        double result = 0.;
        if (semester > 0 && semester <= SEMESTERS.length && !this.tree.isEmpty()) {
            String strSemester = SEMESTERS[--semester];
            if (this.tree.containsKey(strSemester)) {
                Map<String, Integer> subjects = this.tree.get(strSemester);
                if (!subjects.isEmpty()) {
                    int count = 0;
                    for (Integer mark : subjects.values()) {
                        result += mark;
                        count++;
                    }
                    result /= count;
                }
            }
        }
        return result;
    }

    public double countFull() {
        double result = 0.;
        if (!this.tree.isEmpty()) {
            int count = 0;
            for (Map<String, Integer> subjects : this.tree.values()) {
                if (!subjects.isEmpty()) {
                    for (Integer mark : subjects.values()) {
                        result += mark;
                        count++;
                    }
                }
            }
            result /= count;
        }
        return result;
    }

    public String getSemester(int index) {
        return index > 0 && index <= SEMESTERS.length ? SEMESTERS[--index] : null;
    }

    private int getIndexOfSemester(String semester) {
        int index = -1;
        if (semester != null) {
            semester = semester.toUpperCase();
            for (int i = 0; i < SEMESTERS.length; i++) {
                if (semester.equals(SEMESTERS[i])) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private String enterName(String name) {
        if (name != null) {
            name = name.trim().toUpperCase();
            if (name.length() > 1) {
                name = name.charAt(0) + name.substring(1, name.length()).toLowerCase();
            }
        }
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(this.Name).append(" ").append(this.Surname).append(";\n");
        if (!this.tree.isEmpty()) {
            for (Map.Entry<String, Map<String, Integer>> semester : this.tree.entrySet()) {
                sb.append("   Semester ").append(semester.getKey()).append(":\n");
                if (!semester.getValue().isEmpty()) {
                    for (Map.Entry<String, Integer> subject : semester.getValue().entrySet()) {
                        sb.append("      subject: ").append(subject.getKey()).append("; mark: ").
                                append(subject.getValue()).append(";\n");
                    }
                }
            }
        }
        return sb.toString();
    }
}