package adrift;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Maze extends JFrame {

    //Set the box dimensions
    public static int boxSize = 100;
    public static int rows, columns, map[][];
    int startx, starty, endx, endy;
    String mapName, mapTitle;
    Player player;
    DFAList dfalist;

    public Maze(String str, int startx, int starty, int currentMap) {
        dfalist = new DFAList();
        loadMap(str);
        ImageIcon iconLeft = new ImageIcon("res/Images/weiss-left.gif");
        ImageIcon iconRight = new ImageIcon("res/Images/weiss-right.gif");
        ImageIcon iconUp = new ImageIcon("res/Images/weiss-up.gif");
        ImageIcon iconDown = new ImageIcon("res/Images/weiss-down.gif");
        ImageIcon p = new ImageIcon("res/Images/weiss.png");
        ImageIcon cuki = new ImageIcon("res/Images/cookie.png");
        ImageIcon icmap1 = new ImageIcon("res/Images/grassland.png");
        ImageIcon icmap2 = new ImageIcon("res/Images/glacier.png");
        ImageIcon icmap3 = new ImageIcon("res/Images/magma.png");
        ImageIcon icmap4 = new ImageIcon("res/Images/workshop.png");
        ImageIcon icmap5 = new ImageIcon("res/Images/rockcliff.png");
        JLabel labelLeft = new JLabel(iconLeft);
        JLabel labelRight = new JLabel(iconRight);
        JLabel labelUp = new JLabel(iconUp);
        JLabel labelDown = new JLabel(iconDown);
        JLabel labelp = new JLabel(p);
        JLabel labelCook = new JLabel(cuki);
        JLabel map1 = new JLabel(icmap1);
        JLabel map2 = new JLabel(icmap2);
        JLabel map3 = new JLabel(icmap3);
        JLabel map4 = new JLabel(icmap4);
        JLabel map5 = new JLabel(icmap5);
        this.add(labelLeft);
        this.add(labelRight);
        this.add(labelUp);
        this.add(labelDown);
        this.add(labelp);
        this.add(labelCook);
        this.add(map1);
        this.add(map2);
        this.add(map3);
        this.add(map4);
        this.add(map5);
        labelLeft.setVisible(false);
        labelRight.setVisible(false);
        labelUp.setVisible(false);
        labelDown.setVisible(false);
        labelp.setVisible(false);
        labelCook.setVisible(true);
        map1.setVisible(false);
        map2.setVisible(false);
        map3.setVisible(false);
        map4.setVisible(false);
        map5.setVisible(false);

        if (currentMap == 1) {
            map1.setVisible(true);
            map2.setVisible(false);
            map3.setVisible(false);
            map4.setVisible(false);
            map5.setVisible(false);
        } else if (currentMap == 2) {
            map1.setVisible(false);
            map2.setVisible(true);
            map3.setVisible(false);
            map4.setVisible(false);
            map5.setVisible(false);
        } else if (currentMap == 3) {
            map1.setVisible(false);
            map2.setVisible(false);
            map3.setVisible(true);
            map4.setVisible(false);
            map5.setVisible(false);
        } else if (currentMap == 4) {
            map1.setVisible(false);
            map2.setVisible(false);
            map3.setVisible(false);
            map4.setVisible(true);
            map5.setVisible(false);
        } else if (currentMap == 5) {
            map1.setVisible(false);
            map2.setVisible(false);
            map3.setVisible(false);
            map4.setVisible(false);
            map5.setVisible(true);
        }

        this.setResizable(false);
        this.setSize((columns * boxSize) + 50, (rows * boxSize) + 70);
        this.setTitle("Adrift in Swapping Sections");
        this.setLayout(null);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                //Player movement
                if (key == KeyEvent.VK_W) {
                    labelLeft.setVisible(false);
                    labelRight.setVisible(false);
                    labelUp.setVisible(true);
                    labelDown.setVisible(false);
                    labelp.setVisible(false);

                    player.moveUp();

                    labelUp.setLocation((player.x * boxSize) + 23, (player.y * boxSize) + 25);

                    teleport(currentMap);
                }
                if (key == KeyEvent.VK_A) {
                    labelLeft.setVisible(true);
                    labelRight.setVisible(false);
                    labelUp.setVisible(false);
                    labelDown.setVisible(false);
                    labelp.setVisible(false);

                    player.moveLeft();

                    labelLeft.setLocation((player.x * boxSize) + 23, (player.y * boxSize) + 25);

                    teleport(currentMap);
                }
                if (key == KeyEvent.VK_S) {
                    labelLeft.setVisible(false);
                    labelRight.setVisible(false);
                    labelUp.setVisible(false);
                    labelDown.setVisible(true);
                    labelp.setVisible(false);

                    player.moveDown();

                    labelDown.setLocation((player.x * boxSize) + 23, (player.y * boxSize) + 25);

                    teleport(currentMap);
                }
                if (key == KeyEvent.VK_D) {
                    labelLeft.setVisible(false);
                    labelRight.setVisible(true);
                    labelUp.setVisible(false);
                    labelDown.setVisible(false);
                    labelp.setVisible(false);

                    player.moveRight();

                    labelRight.setLocation((player.x * boxSize) + 23, (player.y * boxSize) + 25);

                    teleport(currentMap);
                }

                if (player.x == endx && player.y == endy) {
                    JOptionPane.showMessageDialog(null, "Congratulations, you've beaten the level!", "End Game", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainMenu();
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Center the screen
        this.setLocationRelativeTo(null);

        //Create player
        player = new Player(boxSize);
        player.setVisible(true);
        this.add(player);

        //Set player start position
        this.startx = startx;
        this.starty = starty;

        //Color map
        for (int y = 0; y < columns; y++) {
            for (int x = 0; x < rows; x++) {
                Tile tile = new Tile(x, y);
                tile.setSize(boxSize, boxSize);
                tile.setLocation((x * boxSize) + 23, (y * boxSize) + 25);
                map1.setBounds(0, 0, 444, 444);
                map2.setBounds(0, 0, 444, 444);
                map3.setBounds(0, 0, 444, 444);
                map4.setBounds(0, 0, 444, 444);
                map5.setBounds(0, 0, 444, 444);
                if (map[x][y] == 0) {
                    //Teleporter's color
                    tile.setBackground(Color.GRAY);
//                    tile.setBackground(Color.WHITE);
                } else {
                    //Path color
//                    tile.setBackground(Color.WHITE);
                    tile.setTeleporter(false);

                    //Render player
                    if (x == startx && y == starty) {
                        labelLeft.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        labelRight.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        labelUp.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        labelDown.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        labelp.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        labelp.setVisible(true);

                        player.setLocation((x * boxSize) + 23, (y * boxSize) + 25);
                        player.y = y;
                        player.x = x;
                    }

                    //Render cookie
                    if (map[x][y] == 2) {
                        tile.setBackground(Color.RED);
                        labelCook.setBounds((x * boxSize) + 23, (y * boxSize) + 25, boxSize, boxSize);
                        endx = x;
                        endy = y;
                    }
                }

                tile.setVisible(true);
                this.add(tile);
            }
        }
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new MainMenu();
    }

    public void teleport(int currentMap) {
        if (map[player.x][player.y] == 0) {
            mapName = "Level-";
            int newMap = dfalist.getNewMap(player.x, player.y);

            if (currentMap != newMap) {
                switch (newMap) {
                    case 1:
                        mapName += "1";
                        mapTitle = "map1";
                        break;
                    case 2:
                        mapName += "2";
                        mapTitle = "map2";
                        break;
                    case 3:
                        mapName += "3";
                        mapTitle = "map3";
                        break;
                    case 4:
                        mapName += "4";
                        mapTitle = "map4";
                        break;
                    case 5:
                        mapName += "5";
                        mapTitle = "map5";
                        break;
                }

                mapName += ".txt";

                // uncomment to show map title
//                System.out.println("mapTitle:" + mapTitle);
                new Maze(mapName, player.x, player.y, newMap);

                dispose();
            }
        }
    }

    public void loadMap(String str) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(str));
            String line = "", mapStr = "";
            columns = rows = 4;

            while ((line = br.readLine()) != null) {
                mapStr += line;
            }

            map = new int[columns][rows];

            //Convert string map to int[][] map
            int counter = 0;
            for (int y = 0; y < columns; y++) {
                for (int x = 0; x < rows; x++) {
                    String mapChar = mapStr.substring(counter, counter + 1);
                    if (!mapChar.equals("\r\n") && !mapChar.equals("\n") && !mapChar.equals("\r")) {
                        //If it's a number
                        map[x][y] = Integer.parseInt(mapChar);
                    } else {
                        //If it is a line break
                        x--;
                        System.out.print(mapChar);
                    }
                    counter++;
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to load existing map(if exists), creating new map.");
        }
    }
}
