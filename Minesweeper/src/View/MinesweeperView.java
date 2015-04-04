/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

/**
 *
 * @author Balint
 */
public class MinesweeperView extends JFrame{
    
    // Logic logic;
    public MinesweeperView( /*Logic logic*/) {
        //this.logic = logic;
        setupWindow();
        setupMenu();
        felsoSor();
        initGameField(10);
    }

    private final ActionListener gameButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(((GameButton) e.getSource()).getXB() + " " + ((GameButton) e.getSource()).getYB());
        }
    };

    void felsoSor() {
        JLabel jl = new JLabel("plusz dolgok", SwingConstants.CENTER);
        add(jl, BorderLayout.NORTH);
    }

    void setupWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        pack();
        setTitle("Mine");
        setLayout(new BorderLayout());
    }

    void initGameField(int size) {
        JPanel jp = new JPanel(new GridLayout(10, 10));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GameButton jb = new GameButton(i, j);
                jb.addActionListener(gameButtonListener);
                jp.add(jb);
            }
        }
        add(jp, BorderLayout.CENTER);
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
        menu1 = new JMenu("A Menu");
        menuBar.add(menu1);

        //a submenu
       // menu.addSeparator();
        submenu = new JMenu("Új játék");

        menuItem = new JMenuItem("5×5");
        submenu.add(menuItem);

        menuItem = new JMenuItem("10×10");
        submenu.add(menuItem);
        menu1.add(submenu);
        
        menuItem = new JMenuItem("15×15");
        submenu.add(menuItem);
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
