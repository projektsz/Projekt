package stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    private final List<Winner> winners = new ArrayList<>();

    /**
     * A PrintWriter amivel a statisztikai adatokat irjuk fajlba.
     */
    private PrintWriter out;

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
        try {
            if (isStatfileExists()) {
                Scanner sc = new Scanner(new File("minesweeper.stats"));
                while (sc.hasNext()) {
                    winners.add(new Winner(sc.next(), sc.nextInt(), sc.nextInt(), new Date(sc.nextLong())));
                }
                sc.close();
            }
        } catch (FileNotFoundException ex) {
        }
        createStatFile();
    }

    /**
     * Ellenorzi, hogy letezik-e a statisztika fajl.
     *
     * @return Letezik-e a fajl
     */
    public boolean isStatfileExists() {
        File f = new File("minesweeper.stats");
        return (f.exists() && !f.isDirectory());
    }

    /**
     * Torli a statisztika fajlt.
     */
    public void deleteStatFile() {
        winners.clear();
        if (out != null) {
            out.close();
        }
        File file = new File("minesweeper.stats");
        file.delete();
    }

    /**
     * Letrehozza a statisztika fajlt vagy ha mar letezik akkor megnyitja
     * irasra.
     */
    public void createStatFile() {
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("minesweeper.stats", true)));
        } catch (IOException ex1) {
        }
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
        addWinner(new Winner(name, gametype, time));
    }

    /**
     * Hozzaad egy uj nyertest a statisztikahoz. Fajlba is menti a modositasokat
     * es a winners listaba is felveszi a gyoztest.
     *
     * @param winner A gyoztes
     */
    public void addWinner(Winner winner) {
        if (!isStatfileExists()) {
            createStatFile();
        }
        winners.add(winner);

        out.print(winner.getName());
        out.print(" ");
        out.print(winner.getGametype());
        out.print(" ");
        out.print(winner.getTime());
        out.print(" ");
        out.print(winner.getDate().getTime());
        out.println();
        out.flush();
    }

    /**
     * Megadja ki tartja a rekordot az adott jatektipusban.
     *
     * @param type A jatek tipusa
     * @return A gyoztes
     */
    public Winner getRecorder(int type) {
        Winner gyoztes = null;
        int minido = 0;
        boolean vane = false;

        for (int i = 0; i < winners.size(); i++) {
            if (type == winners.get(i).getGametype()) {
                if (vane == false) {
                    vane = true;
                    minido = winners.get(i).getTime();
                    gyoztes = winners.get(i);
                } else if (winners.get(i).getTime() < minido) {
                    minido = winners.get(i).getTime();
                    gyoztes = winners.get(i);
                }
            }
        }
        return gyoztes;
    }

    /**
     * Megadja, hogy atlagosan mennyi ido alatt teljesitik az adott tipusu
     * jatekot.
     *
     * @param type A jatek tipusa
     * @return Atlag ido
     */
    public float getAverageTime(int type) {
        float ido = 0;
        float atlagido = 0;

        for (int i = 0; i < winners.size(); i++) {
            if (type == winners.get(i).getGametype()) {
                ido++;
                atlagido += winners.get(i).getTime();
            }
        }
        if(ido > 0 && atlagido > 0){
            atlagido = atlagido / ido;
        }else
        {
            atlagido = -1;
        }
        return atlagido;
    }

    /**
     * Megadja a nyertesek szamat a parameterben kapott jatektipusban.
     *
     * @param type A jatek tipusa
     * @return Nyertesek szama
     */
    public int getWinnerCount(int type) {

        int nyertesekszama = 0;

        for (int i = 0; i < winners.size(); i++) {

            if (type == winners.get(i).getGametype()) {
                nyertesekszama++;
            }

        }
        return nyertesekszama;
    }

    /**
     * Az osszes nyertes szamat adja meg.
     *
     * @return Nyertesek szama
     */
    public int getWinnerCount() {
        return winners.size();
    }

    /**
     * Megadja annak a jatekosnak a nevet aki a legtobbszor nyert, a
     * parameterben megkapott jatektipusban.
     *
     * @param type A jatek tipusa amiben keressuk a leggyakoribb nyertest
     * @return Jatekos neve
     */
    public String getMostWinner(int type) {
        Winner[] tomb = winners.toArray(new Winner[winners.size()]);

        if (tomb.length == 0) {
            return null;
        }

        for (int i = tomb.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (tomb[j + 1].getName().compareToIgnoreCase(tomb[j].getName()) < 0) {
                    Winner nev = tomb[j];
                    tomb[j] = tomb[j + 1];
                    tomb[j + 1] = nev;
                }
            }
        }

        int szamol = 1;
        int max = 0;
        String nev = null;

        if (tomb.length == 1) {
            if (type == tomb[0].getGametype()) {
                return tomb[0].getName();
            } else {
                return null;
            }
        }

        for (int i = 0; i < tomb.length - 1; i++) {
            if (type == tomb[i].getGametype()) {
                if (tomb[i].getName().equals(tomb[i + 1].getName())) {
                    szamol++;
                } else {
                    szamol = 1;
                }

                if (szamol > max) {
                    max = szamol;
                    nev = tomb[i].getName();
                }
            }
        }

        return nev;
    }

    /**
     * Megadja annak a jatekosnak a nevet aki a legtobbszor nyert, akarmelyik
     * jatektipusban.
     *
     * @return Jatekos neve
     */
    public String getMostWinner() {
        Winner[] tomb = winners.toArray(new Winner[winners.size()]);

        if (tomb.length == 0) {
            return null;
        }

        for (int i = tomb.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (tomb[j + 1].getName().compareToIgnoreCase(tomb[j].getName()) < 0) {
                    Winner nev = tomb[j];
                    tomb[j] = tomb[j + 1];
                    tomb[j + 1] = nev;
                }
            }
        }

        int szamol = 0;
        int max = 0;
        String nev = tomb[0].getName();

        for (int i = 0; i < tomb.length - 1; i++) {
            if (tomb[i].getName().equals(tomb[i + 1].getName())) {
                szamol++;
            } else {
                szamol = 0;
            }

            if (szamol > max) {
                max = szamol;
                nev = tomb[i].getName();
            }
        }

        return nev;
    }

    /**
     *
     * @return A gyoztesek listaja
     */
    public List<Winner> getWinners() {
        return winners;
    }
}
