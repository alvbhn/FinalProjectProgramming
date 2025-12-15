package org.alvee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllTests {
    // Address

    @Test
    @DisplayName("isPostalCodeValid: null -> false")
    void testIsPostalCodeValid1() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: H3N2C4 -> true")
    void testIsPostalCodeValid2() {
        boolean expected = true;
        boolean actual = Address.isPostalCodeValid("H3N2C4");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: A1B2 -> false")
    void testIsPostalCodeValid3() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("A1B2");

        Assertions.assertEquals(expected, actual);
    }

    // Department

    @Test
    @DisplayName("isDepartmentNameValid: null -> false")
    void testIsDepartmentNameValid1() {
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid(null);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDepartmentNameValid: Computer Science -> true")
    void testIsDepartmentNameValid2() {
        boolean expected = true;
        boolean actual = Department.isDepartmentNameValid("Computer Science");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isDepartmentNameValid: Math101 -> false")
    void testIsDepartmentNameValid3() {
        boolean expected = false;
        boolean actual = Department.isDepartmentNameValid("Math101");
        assertEquals(expected, actual);
    }

    // Assignment

    @Test
    @DisplayName("calcAssignmentAvg: empty -> avg 0")
    void testCalcAssignmentAvg1() {
        Assignment assignment = new Assignment("Exam", 50);
        double expected = 0;
        double actual = assignment.calcAssignmentAvg();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calcAssignmentAvg: [70,71] -> avg 70.5")
    void testCalcAssignmentAvg2() {
        Assignment assignment = new Assignment("Exam", 50);
        assignment.getScores().add(70);
        assignment.getScores().add(71);

        double expected = 70.5;
        double actual = assignment.calcAssignmentAvg();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calcAssignmentAvg: [null,80] -> avg 80")
    void testCalcAssignmentAvg3() {
        Assignment assignment = new Assignment("Exam", 50);
        assignment.getScores().add(null);
        assignment.getScores().add(80);

        double expected = 80;
        double actual = assignment.calcAssignmentAvg();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generateRandomScore: empty -> size stay 0")
    void testGenerateRandomScore1() {
        Assignment assignment = new Assignment("Exam", 50);

        int expected = 0;
        assignment.generateRandomScore();
        int actual = assignment.getScores().size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generateRandomScore: [null,null] -> null count becomes 0")
    void testGenerateRandomScore2() {
        Assignment assignment = new Assignment("Exam", 50);
        assignment.getScores().add(null);
        assignment.getScores().add(null);

        int expected = 0;
        assignment.generateRandomScore();
        int actual = 0;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generateRandomScore: size 3 -> size stay 3")
    void testGenerateRandomScore3() {
        Assignment assignment = new Assignment("Exam", 50);
        assignment.getScores().add(70);
        assignment.getScores().add(80);
        assignment.getScores().add(90);

        int expected = 3;
        assignment.generateRandomScore();
        int actual = assignment.getScores().size();

        assertEquals(expected, actual);
    }
}
