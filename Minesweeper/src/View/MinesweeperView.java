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
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Build the first menu.
        menu = new JMenu("A Menu");
        menuBar.add(menu);

        //a submenu
       // menu.addSeparator();
        submenu = new JMenu("Új játék");

        menuItem = new JMenuItem("5×5");
        submenu.add(menuItem);

        menuItem = new JMenuItem("10×10");
        submenu.add(menuItem);
        menu.add(submenu);
        
        menuItem = new JMenuItem("15×15");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Statisztika");
        menuBar.add(menu);
        
        this.setJMenuBar(menuBar);

    }

}
