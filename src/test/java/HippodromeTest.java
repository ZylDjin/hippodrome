import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class HippodromeTest {

    @Test
    void constructor_ThrowsExceptionIfNullList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_ThrowsExceptionIfEmptyList() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ReturnsCorrectList() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, 1.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move_CallsMoveOnAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner_ReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse1", 1.0, 10.0);
        Horse horse2 = new Horse("Horse2", 1.0, 20.0);
        Horse horse3 = new Horse("Horse3", 1.0, 15.0);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertEquals(horse2, hippodrome.getWinner());
    }
}
