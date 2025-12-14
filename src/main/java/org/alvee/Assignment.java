package org.alvee;

import lombok.*;

import java.util.List;

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
}
