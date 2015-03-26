package stats;

import java.util.Date;

/**
 * Egy gyoztest reprezentalo osztaly.
 *
 * @author Balázs
 */
public class Winner {

    /**
     * A jatekos neve
     */
    private final String name;

    /**
     * A jatek tipusa amiben a jatekos nyert.
     */
    private final int gametype;

    /**
     * Ennyi ido alatt teljesitette a jatekot.
     */
    private final int time;

    /**
     * A jatek teljesitesenek a datuma.
     */
    private final Date date;

    public Winner(String name, int gametype, int time) {
        this.name = name;
        this.gametype = gametype;
        this.time = time;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public int getGametype() {
        return gametype;
    }

    public int getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }
}
