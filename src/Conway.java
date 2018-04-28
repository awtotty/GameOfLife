import java.awt.*;
import java.util.HashMap;

public class Conway {
    HashMap<Point, Boolean> world = new HashMap<Point, Boolean>();

    public Boolean isAlive(Point p) {
        return world.get(p); 
    }
}
