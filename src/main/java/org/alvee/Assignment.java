package org.alvee;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    @Getter
    private List<Integer> scores;

    private static int nextId;

    public double calcAssignmentAvg() {
        if (scores.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer s : scores) {
            sum += s;
        }

        return sum / scores.size();
    }

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
}
