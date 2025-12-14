package org.alvee;

import lombok.*;
import java.util.List;

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
}
