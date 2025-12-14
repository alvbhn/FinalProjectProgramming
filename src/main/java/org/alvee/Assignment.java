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
}
