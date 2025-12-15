package org.alvee;

import lombok.*;

import java.util.ArrayList;
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
    private List<Integer> finalScores;

    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        if (department == null) {
            this.courseId = null;
        }else {
            this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        }

        this.courseName = util.Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    /**
     * checks if the sum of weights of all assignments of that course equals to 100%
     * @return return true if total weight is 100% otherwise false
     */
    public boolean isAssignmentWeightValid() {
        double sum = 0;
        for (Assignment a : assignments) {
            sum += a.getWeight();
        }

        return sum == 100;
    }

    /**
     * adds a student to the student list of the course
     * @param student the student to register
     * @return return true if the student registered otherwise false if already registered or null
     */
    public boolean registerStudent(Student student) {
        if (student == null || registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);

        for (Assignment a : assignments) {
            a.getScores().add(null);
        }

        finalScores.add(null);

        return true;
    }

    /**
     * calculates the weighted average score of a student
     * @return return an array of final weighted scores for students
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double total = 0;

            for (Assignment a : assignments) {
                Integer score = a.getScores().get(i);
                if (score != null) {
                    total += score * (a.getWeight() / 100);
                }
            }
            averages[i] = (int) total;
        }

        return averages;
    }

    /**
     * adds a new assignment to the course
     * @param assignmentName the name of the assignment
     * @param weight the weight of the assignment
     * @return return true always
     */
    public boolean addAssignment(String assignmentName, double weight) {
        assignments.add(new Assignment(assignmentName, weight));

        for (int i = 0; i < registeredStudents.size(); i++) {
            assignments.get(assignments.size() - 1).getScores().add(null);
        }

        return true;
    }

    /**
     * generates random scores for each assignment and student,
     * and calculates the final score for each student
     */
    public void generateScores() {
        for (Assignment a : assignments) {
            a.generateRandomScore();
        }

        int[] averages = calcStudentsAverage();
        finalScores = new ArrayList<>();
        for (int i = 0; i < averages.length; i++) {
            finalScores.add(averages[i]);
        }
    }

    /**
     * displays the scores of a course in a table with the assignment averages
     * and student weighted average
     */
    public void displayScores() {
        System.out.println("Course: " + courseName + "(" + courseId + ")");

        System.out.printf("%20s", "");
        for (Assignment a : assignments) {
            System.out.printf("%15s", a.getAssignmentName());
        }
        System.out.printf("%15s%n", "Final Score");

        int[] averages = calcStudentsAverage();

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

            System.out.printf("%15d%n", averages[i]);
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
        System.out.println();
    }

    /**
     * converts a course to a simple string
     * the string only contains courseId, courseName, credits and departmentName
     * @return the simple string
     */
    public String toSimplifiedString() {
        return courseId + " " + courseName + " " + credits + " " + department.getDepartmentName();
    }

    /**
     * converts a course to a string that contains the courseId, the courseName, the credits,
     * the departmentName the assignments, the registeredStudents
     * (only the studentId, the studentName and the departmentName),
     * and a line to show if the current `isAssignmentWeightValid` is valid or not.
     * @return return a string representation of the course
     */
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
