package org.alvee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.Util;

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

    // Student

    @Test
    @DisplayName("registerCourse: null course -> false")
    void testRegisterCourse1() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        boolean expected = false;
        boolean actual = student.registerCourse(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerCourse: not registered -> true")
    void testRegisterCourse2() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);
        Course course = new Course("Programming", 2.5, null);

        boolean expected = true;
        boolean actual = student.registerCourse(course);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerCourse: already registered -> false")
    void testRegisterCourse3() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);
        Course course = new Course("Programming", 2.5, null);

        student.registerCourse(course);

        boolean expected = false;
        boolean actual = student.registerCourse(course);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("dropCourse: null -> false")
    void testDropCourse1() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        boolean expected = false;
        boolean actual = student.dropCourse(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("dropCourse: course not registered -> false")
    void testDropCourse2() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        boolean expected = false;
        boolean actual = student.dropCourse(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("dropCourse: course registered -> false")
    void testDropCourse3() {
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);
        Course course = new Course("Programming", 2.5, null);

        student.dropCourse(course);

        boolean expected = false;
        boolean actual = student.dropCourse(course);

        Assertions.assertEquals(expected, actual);
    }

    // Assignment

    @Test
    @DisplayName("AssignmentWeightValid: sum = 100 -> true")
    void testIsAssignmentWeightValid1() {
        Course course = new Course("Programming", 2.0, null);
        course.addAssignment("Exam", 50);
        course.addAssignment("Project", 50);

        boolean expected = true;
        boolean actual = course.isAssignmentWeightValid();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("AssignmentWeightValid: sum != 100 -> false")
    void testIsAssignmentWeightValid2() {
        Course course = new Course("Programming", 2.0, null);
        course.addAssignment("Exam", 40);
        course.addAssignment("Project", 50);

        boolean expected = false;
        boolean actual = course.isAssignmentWeightValid();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerStudent: not registered -> true")
    void testRegisterStudent1() {
        Course course = new Course("Programming", 2.0, null);
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        boolean expected = true;
        boolean actual = course.registerStudent(student);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("registerStudent: already registered -> false")
    void testRegisterStudent2() {
        Course course = new Course("Programming", 2.0, null);
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        course.registerStudent(student);

        boolean expected = false;
        boolean actual = course.registerStudent(student);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    @DisplayName("calcStudentsAverage: no students -> empty array")
    void testCalcStudentsAverage1() {
        Course course = new Course("Programming", 2.0, null);

        int expected = 0;
        int actual = course.calcStudentsAverage().length;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("calcStudentsAverage: 1 student 1 assignment -> size 1")
    void testCalcStudentsAverage2() {
        Course course = new Course("Programming", 2.0, null);
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        course.addAssignment("Exam", 100);
        course.registerStudent(student);
        course.getAssignments().get(0).getScores().set(0, 80);

        int expected = 1;
        int actual = course.calcStudentsAverage().length;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("addAssignment: empty -> size becomes 1")
    void testAddAssignment1() {
        Course course = new Course("Programming", 2.0, null);

        course.addAssignment("Exam", 50);

        int expected = 1;
        int actual = course.getAssignments().size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("addAssignment: 2 calls -> size becomes 2")
    void testAddAssignment2() {
        Course course = new Course("Programming", 2.0, null);

        course.addAssignment("Exam", 50);
        course.addAssignment("Project", 50);

        int expected = 2;
        int actual = course.getAssignments().size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generateScores: no assignments -> no crash, finalScores size 0")
    void testGenerateScores1() {
        Course course = new Course("Programming", 2.0, null);

        course.generateScores();

        int expected = 0;
        int actual = course.getFinalScores().size();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("generateScores: 1 student 1 assignment -> finalScores size 1")
    void testGenerateScores2() {
        Course course = new Course("Programming", 2.0, null);
        Student student = new Student("Alvee", Student.Gender.MALE, null, null);

        course.addAssignment("Exam", 100);
        course.registerStudent(student);

        course.generateScores();

        int expected = 1;
        int actual = course.getFinalScores().size();

        Assertions.assertEquals(expected, actual);
    }

    // Util

    @Test
    @DisplayName("toTitleCase: null -> null")
    void testToTitleCase1() {
        String expected = null;
        String actual = Util.toTitleCase(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase: empty -> empty")
    void testToTitleCase2() {
        String expected = "";
        String actual = Util.toTitleCase("");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("toTitleCase: mixed case -> title case")
    void testToTitleCase3() {
        String expected = "Programming";
        String actual = Util.toTitleCase("ProGRAmMiNG");

        Assertions.assertEquals(expected, actual);
    }
}
