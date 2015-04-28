/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import model.FieldIsMineException;
import model.FieldIsPushedException;
import model.IModel;
import model.IntPair;
import model.Model;
import stats.Statistics;

public class MinesweeperView extends JFrame {

    private JPanel gamePanel = new JPanel(new BorderLayout());
    private int size = 0;
    private IModel logic = new Model();
    private JLabel felsoSor;
    private int time;
    private String name = "";
    private Timer timer = new Timer(1000, new timerActionListener());
    private JButton[][] gameButtons;

    public MinesweeperView() {
        setupWindow();
        setupMenu();
        createStartView();
    }

    class timerActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            time++;
            felsoSor.setText(getTimeFromInt(time));
        }
    }

    public String getTimeFromInt(int time) {
        String t;
        int minutes = time / 60;
        int secunds = time - (minutes * 60);
        if (secunds < 10) {
            t = "" + minutes + ":0" + secunds;
        } else {
            t = "" + minutes + ":" + secunds;
        }
        return t;
    }

    private final ActionListener gameButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                logic.push(((GameButton) e.getSource()).getXB(), ((GameButton) e.getSource()).getYB());
                int num = logic.numberOfNearlyMines(((GameButton) e.getSource()).getXB(), ((GameButton) e.getSource()).getYB());
                ((GameButton) e.getSource()).setText(Integer.toString(num));
                if (num == 0) {
                    Collection<IntPair> arr = logic.findEmptyNeighbors(((GameButton) e.getSource()).getXB(), ((GameButton) e.getSource()).getYB());
                    for (IntPair x : arr) {
                        gameButtons[x.first][x.second].setText(Integer.toString(logic.numberOfNearlyMines(x.first, x.second)));
                    }
                }

            } catch (FieldIsPushedException ex) {
                //TODO
            } catch (FieldIsMineException ex) {
                ((GameButton) e.getSource()).setBackground(Color.RED);
                timer.stop();
                JOptionPane.showMessageDialog(null, "VESZTETTÉL");
                startNewGame(size);
                return;
            }
            if (logic.isFine()) {
                timer.stop();
                Statistics.getInstance().addWinner(name, size, time);
                JOptionPane.showMessageDialog(null, "NYERTÉL");
                startNewGame(size);
            }
        }
    };

    private final ActionListener menuListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!"".equals(name)) {
                switch (((JMenuItem) e.getSource()).getText()) {
                    case "5×5":
                        System.out.println("5");
                        startNewGame(5);
                        break;
                    case "10×10":
                        System.out.println("10");
                        startNewGame(10);
                        break;
                    case "15×15":
                        System.out.println("15");
                        startNewGame(15);
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Add meg a neved");
            }
        }
    };

    public void startNewGame(int size) {
        gamePanel.removeAll();
        gamePanel.revalidate();
        gamePanel.repaint();
        felsoSor();
        initGameField(size);
        logic.createNew(size, size, 10);
        felsoSor.setText("0:00");
        time = 0;
        timer.restart();
    }

    void felsoSor() {
        felsoSor = new JLabel("0:00", SwingConstants.CENTER);
        gamePanel.add(felsoSor, BorderLayout.NORTH);
    }

    void setupWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) - 650) / 2, ((Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) - 650) / 2);
        setPreferredSize(new Dimension(650, 650));
        pack();
        setTitle("Mine");
        add(gamePanel);
    }

    void initGameField(int size) {
        JPanel jp = new JPanel(new GridLayout(size, size));
        gameButtons = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GameButton jb = new GameButton(i, j);
                jb.addActionListener(gameButtonListener);
                jp.add(jb);
                gameButtons[i][j] = jb;
            }
        }
        gamePanel.add(jp, BorderLayout.CENTER);
    }

    private void createStartView() {
        gamePanel.removeAll();
        gamePanel.revalidate();
        gamePanel.repaint();

        JPanel jp = new JPanel(new GridLayout(7, 1));
        jp.setBorder(new EmptyBorder(90, 100, 90, 100));
        gamePanel.add(jp, BorderLayout.CENTER);

        JTextField jtf = new JTextField();
        JButton jb = new JButton("START");

        jtf.setBorder(BorderFactory.createLineBorder(Color.decode("#2C6791")));
        String[] petStrings = {"5×5", "10×10", "15×15"};
        JComboBox cb = new JComboBox(petStrings);
        jp.add(cb);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(jtf.getText())) {
                    switch (cb.getSelectedItem().toString()) {
                        case "5×5":
                            size = 5;
                            break;
                        case "10×10":
                            size = 10;
                            break;
                        case "15×15":
                            size = 15;
                            break;
                    }
                    logic.createNew(size, size, 10);
                    name = jtf.getText();
                    startNewGame(size);
                } else {
                    JOptionPane.showMessageDialog(null, "Add meg a neved");
                }
            }
        });
        jp.add(new JLabel("Nev:"));
        jp.add(jtf);
        jp.add(new JLabel("Méret:"));
        jp.add(cb);
        jp.add(new JLabel());
        jp.add(jb);
        jp.add(new JLabel());

    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1, statMenu, submenu;
        JMenu rekorderMenu, averageTimeMenu, winnerCountMenu, mostWinnerMenu;
        JMenuItem menuItem;

        JMenuItem rekorderItem1, rekorderItem2, rekorderItem3;
        JMenuItem averageTimeItem;
        JMenuItem winnerCountItem1, winnerCountItem2, winnerCountItem3, winnerCountItem4;
        JMenuItem mostWinnerItem1, mostWinnerItem2, mostWinnerItem3, mostWinnerItem4;

        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;
//        Statistics.getInstance()

        //Build the first menu.
        menu1 = new JMenu("Menu");
        menuBar.add(menu1);

        menuItem = new JMenuItem("Új játékos");
        menu1.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createStartView();
            }
        });

        //a submenu
        // menu.addSeparator();
        submenu = new JMenu("Új játék");

        menuItem = new JMenuItem("5×5");
        submenu.add(menuItem);
        menuItem.addActionListener(menuListener);

        menuItem = new JMenuItem("10×10");
        submenu.add(menuItem);
        menuItem.addActionListener(menuListener);
        menu1.add(submenu);

        menuItem = new JMenuItem("15×15");
        submenu.add(menuItem);
        menuItem.addActionListener(menuListener);
        menu1.add(submenu);

        //Build second menu in the menu bar.
        statMenu = new JMenu("Statisztika");
        menuBar.add(statMenu);
        //rekorder submenu
        rekorderMenu = new JMenu("Rekorderek");
        statMenu.add(rekorderMenu);

        rekorderItem1 = new JMenuItem("Rekorder (5x5)");
        rekorderItem2 = new JMenuItem("Rekorder (10x10)");
        rekorderItem3 = new JMenuItem("Rekorder (15x15)");

        rekorderMenu.add(rekorderItem1);
        rekorderMenu.add(rekorderItem2);
        rekorderMenu.add(rekorderItem3);
        //average time submenu
        averageTimeMenu = new JMenu("Átlagos idők");
        statMenu.add(averageTimeMenu);

        averageTimeItem = new JMenuItem("Átlagos idő");
        averageTimeMenu.add(averageTimeItem);

        //winner count submenu
        winnerCountMenu = new JMenu("Nyertesek száma");
        statMenu.add(winnerCountMenu);

        winnerCountItem1 = new JMenuItem("Győztesek száma (5x5)");
        winnerCountItem2 = new JMenuItem("Győztesek száma (10x10)");
        winnerCountItem3 = new JMenuItem("Győztesek száma (15x15)");
        winnerCountItem4 = new JMenuItem("Győztesek száma (összes típuson)");

        winnerCountMenu.add(winnerCountItem1);
        winnerCountMenu.add(winnerCountItem2);
        winnerCountMenu.add(winnerCountItem3);
        winnerCountMenu.add(winnerCountItem4);

        //most winner submenu
        mostWinnerMenu = new JMenu("Legtöbbet nyerő játékosok");
        statMenu.add(mostWinnerMenu);

        mostWinnerItem1 = new JMenuItem("Legtöbbet nyerő (5x5)");
        mostWinnerItem2 = new JMenuItem("Legtöbbet nyerő (10x10)");
        mostWinnerItem3 = new JMenuItem("Legtöbbet nyerő (15x15)");
        mostWinnerItem4 = new JMenuItem("Legtöbbet nyerő (összes típuson)");

        mostWinnerMenu.add(mostWinnerItem1);
        mostWinnerMenu.add(mostWinnerItem2);
        mostWinnerMenu.add(mostWinnerItem3);
        mostWinnerMenu.add(mostWinnerItem4);
        this.setJMenuBar(menuBar);

    }

}
