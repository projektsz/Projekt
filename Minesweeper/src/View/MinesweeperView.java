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
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
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
import stats.Winner;

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
                        size = 5;
                        startNewGame(5);
                        break;
                    case "10×10":
                        System.out.println("10");
                        size = 10;
                        startNewGame(10);
                        break;
                    case "15×15":
                        System.out.println("15");
                        size = 15;
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
        logic.createNew(size);
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
                    logic.createNew(size);
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
    private String averageTimeHiba(){
        return "Nincs rögzített eredmény!";
    }
    private void averageTime(int size){        
        if(size == 5){
            if(Statistics.getInstance().getAverageTime(5) < 0){
                JOptionPane.showMessageDialog(null,"Az átlagos idő 5x5-ös táblán: "+ System.lineSeparator() + averageTimeHiba(), "Átlagos idő", 1);        
            }
            else{
                JOptionPane.showMessageDialog(null,"Az átlagos idő 5x5-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getAverageTime(5), "Átlagos idő", 1);    
                //JOptionPane.showMessageDialog()
            }
        }
        else if(size == 10){
            if(Statistics.getInstance().getAverageTime(10) < 0){
                JOptionPane.showMessageDialog(null,"Az átlagos idő 10x10-ös táblán: "+ System.lineSeparator() + averageTimeHiba(), "Átlagos idő", 1);                   
            }
            else{
                JOptionPane.showMessageDialog(null,"Az átlagos idő 10x10-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getAverageTime(10), "Átlagos idő", 1);    
            }
        }
        else if(size == 15){
            if(Statistics.getInstance().getAverageTime(15) < 0){
                JOptionPane.showMessageDialog(null,"Az átlagos idő 15x15-ös táblán: "+ System.lineSeparator() + averageTimeHiba(), "Átlagos idő", 1);                   
            }
            else{
                JOptionPane.showMessageDialog(null,"Az átlagos idő 15x15-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getAverageTime(15), "Átlagos idő", 1);    
            }
        }
    }
    private String winnerCoundHiba(){
        return "Még nem nyert senki ezen a táblán!";
    }
    private void winnerCount(int size){
        //Összes győztes száma
        if(size == 0){
            if(Statistics.getInstance().getWinnerCount() == 0){
                JOptionPane.showMessageDialog(null,"Győztesek száma az összes táblán: "+ System.lineSeparator() + winnerCoundHiba(), "Győztesek száma", 1);    
            }else{
                JOptionPane.showMessageDialog(null,"Győztesek száma az összes táblán: "+ System.lineSeparator() + Statistics.getInstance().getWinnerCount(), "Győztesek száma", 1);    
            }
        }    
        //5x5-ös győztesek száma
        else if(size == 5){
            if(Statistics.getInstance().getWinnerCount(5) == 0){
                JOptionPane.showMessageDialog(null,"Győztesek száma az 5x5-ös táblán: "+ System.lineSeparator() + winnerCoundHiba(), "Győztesek száma", 1);    
            }else{            
                JOptionPane.showMessageDialog(null,"Győztesek száma az 5x5-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getWinnerCount(5), "Győztesek száma", 1);    
            }
        }
        //10x10-es győztesek száma
        else if(size == 10){
            if(Statistics.getInstance().getWinnerCount(10) == 0){
                JOptionPane.showMessageDialog(null,"Győztesek száma az 10x10-ös táblán: "+ System.lineSeparator() + winnerCoundHiba(), "Győztesek száma", 1);    
            }else{                
                JOptionPane.showMessageDialog(null,"Győztesek száma az 10x10-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getWinnerCount(10), "Győztesek száma", 1);      
            }
        }
        ////15x15-ös győztesek száma
        else if(size == 15){
            if(Statistics.getInstance().getWinnerCount(15) == 0){
                JOptionPane.showMessageDialog(null,"Győztesek száma az 15x15-ös táblán: "+ System.lineSeparator() + winnerCoundHiba(), "Győztesek száma", 1);    
            }else{                
                JOptionPane.showMessageDialog(null,"Győztesek száma az 15x15-ös táblán: "+ System.lineSeparator() + Statistics.getInstance().getWinnerCount(15), "Győztesek száma", 1);       
            }
        }
    }
    
    private void winners(){
        
        List<Winner> nyertesek = Statistics.getInstance().getWinners();     
        
        JFrame frame = new JFrame("Győztesek");
        class Lista extends JPanel{
            JList list;
            DefaultListModel model;
            JButton oke;
            Lista(){
                
                setLayout(new BorderLayout());
                model = new DefaultListModel();
                list = new JList(model);                              
                JScrollPane pane = new JScrollPane(list);

                for(Winner winner: nyertesek){
                    model.addElement(winner.toString());
                }

                oke = new JButton("OK");
                oke.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
                
                add(pane);
                add(oke, BorderLayout.PAGE_END);
       
            }
        }               
        frame.setLocation(((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) + 65) / 2, ((Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) + 400) / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Lista());
        frame.setSize(600, 200);
        frame.setVisible(true);
   
    }
    
    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1, statMenu, submenu;
        JMenu rekorderMenu, averageTimeMenu, winnerCountMenu, mostWinnerMenu;
        JMenuItem menuItem;

        JMenuItem winnersMenu;
        JMenuItem rekorderItem1, rekorderItem2, rekorderItem3;
        JMenuItem averageTimeItem1, averageTimeItem2, averageTimeItem3;
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
        
        //winners submenu
        winnersMenu = new JMenuItem("Győztesek listája");

        winnersMenu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                winners();
            }
        });
        
        statMenu.add(winnersMenu);        

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
        
        averageTimeItem1 = new JMenuItem("Átlagos idő (5x5)");
        averageTimeItem2 = new JMenuItem("Átlagos idő (10x10)");
        averageTimeItem3 = new JMenuItem("Átlagos idő (15x15)");
        averageTimeItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageTime(5);
            }
        });
        averageTimeItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageTime(10);
            }
        });
        averageTimeItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                averageTime(15);
            }
        });
   
        averageTimeMenu.add(averageTimeItem1);
        averageTimeMenu.add(averageTimeItem2);
        averageTimeMenu.add(averageTimeItem3);

        //winner count submenu
        winnerCountMenu = new JMenu("Nyertesek száma");
        statMenu.add(winnerCountMenu);

        winnerCountItem1 = new JMenuItem("Győztesek száma (5x5)");
        winnerCountItem2 = new JMenuItem("Győztesek száma (10x10)");
        winnerCountItem3 = new JMenuItem("Győztesek száma (15x15)");
        winnerCountItem4 = new JMenuItem("Győztesek száma (összes típuson)");
        
        winnerCountItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerCount(5);
            }
        });
        winnerCountItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerCount(10);
            }
        });
        winnerCountItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerCount(15);
            }
        });
        winnerCountItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerCount(0);
            }
        });

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
