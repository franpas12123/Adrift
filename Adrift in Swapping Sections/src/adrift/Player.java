package adrift;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Image;

public class Player extends JPanel {

    int x, y, boxSize;

    public Player(int panelSize) {
        this.boxSize = panelSize;
        //this.setBackground(Color.getHSBColor(0.3f, 0.3f, 1));//orig color
        this.setBackground(Color.WHITE);//match bg color
        this.setSize(Maze.boxSize, Maze.boxSize);
    }

    public void moveLeft() {
        if (x > 0) {
            this.setLocation(this.getX() - boxSize, this.getY());
            x--;
        }
    }

    public void moveRight() {
        if (x < Maze.columns - 1) {
            this.setLocation(this.getX() + boxSize, this.getY());
            x++;
        }
    }

    public void moveUp() {
        if (y > 0) {
            this.setLocation(this.getX(), this.getY() - boxSize);
            y--;
        }
    }

    public void moveDown() {
        if (y < Maze.rows - 1) {
            this.setLocation(this.getX(), this.getY() + boxSize);
            y++;
        }
    }
}
