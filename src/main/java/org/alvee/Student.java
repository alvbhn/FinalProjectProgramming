package org.alvee;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;

    private static int nextId = 1;

    public enum Gender {
        MALE, FEMALE
    }

    public boolean registerCourse(Course course) {
        if (course == null || registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);
    }


    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%05d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }
}

