package stats;

import java.util.ArrayList;
import java.util.List;

/**
 * A jatekkal kapcsolatos statisztikai adatok lekerdezesere szolgalo osztaly. Az
 * osztaly singleton, tehat nem lehet peldanyositani kivulrol. Csak a
 * getInstance() fuggvennyel lehet lekerni az egyetlen objektumot belole.
 * Fajlbol beolvassa a statisztikai adatokat.
 *
 * @author Balázs
 */
public class Statistics {

    /**
     * Az egyetlen Statistics objektum.
     */
    private static final Statistics instance = new Statistics();

    /**
     * Az eddigi nyertesek listaja.
     */
    private final List<Winner> winneres = new ArrayList<>();

    /**
     * Visszaadja az egyetlen Statistics objektumot
     *
     * @return Statistics objektum.
     */
    public static Statistics getInstance() {
        return instance;
    }

    /**
     * Letrehozza a Statistics objektumot, betolti fajlbol az eddigi
     * statisztikai adatokat. Ha nincs ilyen fajl, akkor letehoz egy uj, ures
     * fajlt a statisztikak tarolasara.
     */
    private Statistics() {

    }

    /**
     * Hozzaad egy uj nyertest a statisztikahoz. Fajlba is menti a modositasokat
     * es a winners listaba is felveszi a gyoztest.
     *
     * @param name A jatekos neve
     * @param gametype A jatek tipusa
     * @param time Az ido ami alatt teljesitette a jatekot a jatekos.
     */
    public void addWinner(String name, int gametype, int time) {

    }

    /**
     * Hozzaad egy uj nyertest a statisztikahoz. Fajlba is menti a modositasokat
     * es a winners listaba is felveszi a gyoztest.
     *
     * @param winner A gyoztes
     */
    public void addWinner(Winner winner) {

    }

    /**
     * Megadja ki tartja a rekordot az adott jatektipusban.
     *
     * @param type A jatek tipusa
     * @return A gyoztes
     */
    public Winner getRecorder(int type) {
        return new Winner("", 0, 0);
    }

    /**
     * Megadja, hogy atlagosan mennyi ido alatt teljesitik az adott tipusu
     * jatekot.
     *
     * @param type A jatek tipusa
     * @return Atlag ido
     */
    public float getAverageTime(int type) {
        return 0;
    }

    /**
     * Megadja a nyertesek szamat a parameterben kapott jatektipusban.
     *
     * @param type A jatek tipusa
     * @return Nyertesek szama
     */
    public int getWinnerCount(int type) {
        return 0;
    }

    /**
     * Az osszes nyertes szamat adja meg.
     *
     * @return Nyertesek szama
     */
    public int getWinnerCount() {
        return 0;
    }

    /**
     * Megadja annak a jatekosnak a nevet aki a legtobbszor nyert, a
     * parameterben megkapott jatektipusban.
     *
     * @param type A jatek tipusa amiben keressuk a leggyakoribb nyertest
     * @return Jatekos neve
     */
    public String getMostWinner(int type) {
        return "";
    }

    /**
     * Megadja annak a jatekosnak a nevet aki a legtobbszor nyert, akarmelyik
     * jatektipusban.
     *
     * @return Jatekos neve
     */
    public String getMostWinner() {
        return "";
    }
}
