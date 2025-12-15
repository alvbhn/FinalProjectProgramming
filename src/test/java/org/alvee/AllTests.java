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
}
