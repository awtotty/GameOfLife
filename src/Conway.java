import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.List;


public class Conway {
    HashMap<Point, Boolean> world = new HashMap<Point, Boolean>();
    Set<Point> keys;
    Set<Boolean> vals;
    int generation = 1 ;
    int rows;
    int cols;

    // Creates a random 20x20 world
    public Conway() {
        for (int i = 0; i < 20; i++) {


        }
    }


    public void advanceGeneration() {
        generation++;

        // Make a new world to build
        HashMap<Point, Boolean> nextWorld = new HashMap<Point, Boolean>();

        // Handle live cells (rules 1-3)
        for (Point p : keys) {
            int n = getNumNeighbors(p);
            if (n == 2 || n == 3) {
                nextWorld.put(p, true);
            }
        }

        // Handle dead cells (rule 4)
    }

    public int getNumNeighbors(Point p) {
        int px = (int) p.getX();
        int py = (int) p.getY();
        int neighbors = 0;

        for (int x = px-1; x <= px+1; x++) {
            for (int y = py-1; y <= py+1; y++) {
                // neighbor exists and is not the actual point
                if (world.containsKey(new Point(x,y)) && (px != x && py != y)) {
                   neighbors++;
                }
            }
        }

        return neighbors;
    }
}
