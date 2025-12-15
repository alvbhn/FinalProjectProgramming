package org.alvee;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;

    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
    }

    public boolean isAssignmentWeightValid() {
        double sum = 0;

        for (Assignment a : assignments) {
            sum += a.getWeight();
        }

        return sum == 100;
    }

    public boolean registerStudent(Student student) {
        if (student == null || registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment a : assignments) {
            a.getScores().add(null);
        }

        return true;
    }

    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double total = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null) {
                    total += score * a.getWeight() / 100;
                }
            }

            averages[i] = (int) total;
        }

        return averages;
    }

    public boolean addAssignment(String assignmentName, double weight) {
        assignments.add(new Assignment(assignmentName, weight));
        return true;
    }

    public void generateScores() {
        for (Assignment a : assignments) {
            a.generateRandomScore();
        }

        calcStudentsAverage();
    }

    public void displayScores() {
        System.out.println("Course: " + courseName + "(" + courseId + ")");

        System.out.printf("%20s", "");
        for (Assignment a : assignments) {
            System.out.printf("%15s", a.getAssignmentName());
        }
        System.out.printf("%15s%n", "Final Score");

        int[] finalScores = calcStudentsAverage();

        for (int i = 0; i < registeredStudents.size(); i++) {
            Student s = registeredStudents.get(i);
            System.out.printf("%20s", s.getStudentName());

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null) {
                    System.out.printf("%15d", score);
                } else {
                    System.out.printf("%15s", " ");
                }
            }

            System.out.printf("%15d%n", finalScores[i]);
        }

        System.out.printf("%20s", "Average");

        for (Assignment a : assignments) {
            int sum = 0;
            int count = 0;

            for (Integer s : a.getScores()) {
                if (s != null) {
                    sum += s;
                    count++;
                }
            }

            int avg = (count == 0) ? 0 : sum / count;
            System.out.printf("%15d", avg);
        }
    }

    public String toSimplifiedString() {
        return courseId + " " + courseName + " " + credits + " " + department.getDepartmentName();
    }

    @Override
    public String toString() {
        String assignmentList = "";
            for (int i = 0; i < assignments.size(); i++) {
                assignmentList += assignments.get(i).toString();
                if (i < assignments.size() - 1) {
                    assignmentList += ", ";
                }
            }

        String studentList = "";
            for (int i = 0; i < registeredStudents.size(); i++) {
                studentList += registeredStudents.get(i).toSimplifiedString();
                if (i < registeredStudents.size() - 1) {
                    studentList += ", ";
                }
            }

        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department='" + department.getDepartmentName() + '\'' +
                ", assignments=" + assignmentList +
                ", registeredStudents=" + studentList +
                ", assignmentWeightsValid=" + (isAssignmentWeightValid() ? "Yes" : "No") +
                '}';
    }
}
