import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;



public class Hippodrome {
    private static final Logger logger = LogManager.getLogger(Hippodrome.class);

    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (horses == null) {
            logger.error("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        }
        if (horses.isEmpty()) {
            logger.error("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.debug("Создание Hippodrome, лошадей [{}]", horses.size());
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream().max((h1, h2) -> Double.compare(h1.getDistance(), h2.getDistance())).orElse(null);
    }
}

