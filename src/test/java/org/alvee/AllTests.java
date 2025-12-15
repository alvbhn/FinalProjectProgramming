package org.alvee;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllTests {
    // Address
    @Test
    @DisplayName("isPostalCodeValid: null -> false")
    void isPostalCodeValid1() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: H3N2C4 -> true")
    void isPostalCodeValid2() {
        boolean expected = true;
        boolean actual = Address.isPostalCodeValid("H3N2C4");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("isPostalCodeValid: A1B2 -> false")
    void isPostalCodeValid3() {
        boolean expected = false;
        boolean actual = Address.isPostalCodeValid("A1B2");

        Assertions.assertEquals(expected, actual);
    }
}
