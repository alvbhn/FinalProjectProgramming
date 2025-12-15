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

    /**
     * registers a course for the student
     * adds the course to the student's registered courses list
     * adds the student to the registered students list
     * appends null for the scores of each assignment of the course
     * if the course is null or already registered, return false
     * @param course the course to register
     * @return returns true if the course is registered otherwise false
     */
    public boolean registerCourse(Course course) {
        if (course == null || registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.getRegisteredStudents().add(this);

        for (Assignment a : course.getAssignments()) {
            a.getScores().add(null);
        }

        return true;
    }

    /**
     * drops a course for the student
     * removes the course from the student's registered courses list
     * removes the student from the course's registered students list
     * if the course is not registered, return false
     * @param course the course to drop
     * @return return true if the course is dropped otherwise false
     */
    public boolean dropCourse(Course course) {
        if (course == null || !registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);

        return true;
    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%05d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    /**
     * converts a student to a simple string
     * the string only contains studentId, studentName and departmentName
     * @return return the simple string
     */
    public String toSimplifiedString() {
        return studentId + " " + studentName + " " + department.getDepartmentName();
    }

    /**
     * converts a student to a string that contains the studentId, the studentName,
     * the gender, the address and the department, and the registeredCourses
     * (only the courseId, the courseName, and the departmentName)
     * @return return a string representation of the student
     */
    @Override
    public String toString() {
        String coursesStr = "";
        for (int i = 0; i < registeredCourses.size(); i++) {
            Course c = registeredCourses.get(i);

            coursesStr += c.getCourseId() + " " + c.getCourseName() + " " + c.getDepartment().getDepartmentName();

            if (i < registeredCourses.size() - 1) {
                coursesStr += ", ";
            }
        }

        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + coursesStr +
                '}';
    }
}
