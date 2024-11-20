import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class HippodromeTest {

    @Test
    void constructor_NullHorses_ShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void getWinner_ShouldReturnHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse1", 10, 100);
        Horse horse2 = new Horse("Horse2", 10, 200);
        Horse horse3 = new Horse("Horse3", 10, 150);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));
        assertEquals(horse2, hippodrome.getWinner());
    }
}
