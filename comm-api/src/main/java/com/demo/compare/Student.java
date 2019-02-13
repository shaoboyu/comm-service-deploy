package com.demo.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 排序
 *
 * @author: yushaobo
 * @create: 19-2-13
 **/
public class Student implements Comparable<Student> {

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return this.age - (o.age);
    }

    public static void main(String[] args) {

        List<Student> studentlist = new ArrayList<>();

        Collections.sort(studentlist, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });
    }
}
