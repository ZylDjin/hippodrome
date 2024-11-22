
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.*;


class HorseTest {

    @Test
    void throwExceptionIfNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 3.4));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void throwExceptionIfNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 3.4));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void throwExceptionIfSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("ValidName", -1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void throwExceptionIfDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("ValidName", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ReturnsCorrectName() {
        Horse horse = new Horse("TestName", 1.0);
        assertEquals("TestName", horse.getName());
    }

    @Test
    void getSpeed_ReturnsCorrectSpeed() {
        Horse horse = new Horse("TestName", 5.0);
        assertEquals(5.0, horse.getSpeed());
    }

    @Test
    void getDistance_ReturnsCorrectDistance() {
        Horse horse = new Horse("TestName", 5.0, 10.0);
        assertEquals(10.0, horse.getDistance());
    }

    @Test
    void move_CalculatesCorrectDistance() {
        try (MockedStatic<Horse> mocked = Mockito.mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("TestName", 2.0, 10.0);
            horse.move();
            assertEquals(11.0, horse.getDistance());
        }
    }
}