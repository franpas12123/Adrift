package adrift;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu {

    JFrame Menu = new JFrame("Adrift in Swapping Sections");
    JButton Start = new JButton("Play");
    JButton Exit = new JButton("Exit");
    ImageIcon picture = new ImageIcon("res/Images/intro.png");
    JLabel imageLabel = new JLabel(picture);
    int menuWidth = 100; //Width of each button/item on display
    int menuHeight = 30;//Height of each button/item on display
    int menuY = 460; //Button/item location on display
    int WIDTH = 490;
    int HEIGHT = 530;

    public MainMenu() {
        //Menu Variables
        Menu.setResizable(false);
        Menu.setSize(WIDTH, HEIGHT);
        Menu.setLayout(null);
        Menu.setLocationRelativeTo(null);
        Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Start Button Variables
        Start.setSize(menuWidth, menuHeight);
        Start.setLocation(10, menuY);
        Menu.add(Start);

        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new Maze("Level-1.txt", 0, 3, 1);
                Menu.setVisible(false);
            }
        });

        //Exit Button Variables
        Exit.setSize(menuWidth, menuHeight);
        Exit.setLocation(375, menuY);
        Menu.add(Exit);
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Display Picture
        imageLabel.setBounds((WIDTH - 412) / 2, 25, 412, 412);
        imageLabel.setVisible(true);
        Menu.add(imageLabel);
        Menu.setVisible(true);
    }
}
