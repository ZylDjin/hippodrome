import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    @Test
    void constructor_NullName_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 10, 0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructor_BlankName_ShouldThrowException(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 10, 0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void move_ShouldCalculateNewDistance() {
        Horse horse = spy(new Horse("TestHorse", 10, 5));
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            assertEquals(10 * 0.5 + 5, horse.getDistance());
        }
    }
}