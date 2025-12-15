package org.alvee;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private List<Integer> scores;

    private static int nextId;

    /**
     * calculates the average score for the assignment
     * @return return the average score for the assignment and if there are no scores, return 0
     */
    public double calcAssignmentAvg() {
        int sum = 0;
        int count = 0;

        for (Integer s : scores) {
            if (s != null) {
                sum += s;
                count++;
            }
        }

        if (count == 0) {
            return 0;
        }

        return (double) sum / count;
    }

    /**
     *  generates random scores for all students in an assignment
     *  based on a random number between 0 and 10
     */
    public void generateRandomScore() {
        Random random = new Random();

        for (int i = 0; i < scores.size(); i++) {
            int r = random.nextInt(11);
            int score;

            if (r == 0) {
                score = random.nextInt(60);
            } else if (r == 1 || r == 2) {
                score = 60 + random.nextInt(10);
            } else if (r == 3 || r == 4) {
                score = 70 + random.nextInt(10);
            } else if (r >= 5 && r <= 8) {
                score = 80 + random.nextInt(10);
            } else {
                score = 90 +random.nextInt(11);
            }

            scores.set(i, score);
        }
    }

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.format("A%02d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
