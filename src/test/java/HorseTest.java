import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void throwExceptionIfNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 3.4));
    }

    @Test
    void showMessageIfNameIsNull() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\f", "\r", ""})
    void throwExceptionIfSpacesInName(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 3.4));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t", "\n", "\f", "\r", ""})
    void showMessageIfSpacesInName(String name) {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(name, 3.4));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void throwExceptionIfSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -4.0));
    }

    @Test
    void showMessageIfSpeedIsNegative() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -4));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void throwExceptionIfDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 4.0, -5.0));
    }

    @Test
    void showMessageIfDistanceIsNegative() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 4, -5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String expectedName = "testHorse";
        Horse testHorse = new Horse("testHorse", 4.0);
        assertEquals(expectedName, testHorse.getName());
    }

    @Test
    void getSpeedTest() {
        double expectedSpeed = 4.0;
        Horse testHorse = new Horse("testHorse", 4.0);
        assertEquals(expectedSpeed, testHorse.getSpeed());
    }

    @Test
    void getZeroDistance() {
        double expectedDistance = 0.0;
        Horse testHorse = new Horse("testHorse",5.0);
        assertEquals(expectedDistance, testHorse.getDistance());
    }

    @Test
    void getDistanceTest() {
        double expectedDistance = 4.0;
        Horse testHorse = new Horse("testHorse",5.0, 4.0);
        assertEquals(expectedDistance, testHorse.getDistance());
    }

    @Test
    void checkMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)){
            mock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.4);
            Horse horse = new Horse("test", 2.3);
            horse.move();
            mock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({"2.5, 3.0", "4.5, 4.4", "6.5, 0", "3.4, 4.0", "2.6, 0"})
    void calculateCorrectDistance(double speed, double distance) {
        try (MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)){
            mock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.4);
            Horse horse = new Horse("test", speed, distance);
            double expected = horse.getDistance() + horse.getSpeed() * 0.4;
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }

}