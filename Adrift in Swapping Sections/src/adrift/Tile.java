package adrift;

import javax.swing.JPanel;

public class Tile extends JPanel {

    int x, y;
    boolean isTeleporter = true;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setTeleporter(boolean isWall) {
        this.isTeleporter = isTeleporter;
    }
}
