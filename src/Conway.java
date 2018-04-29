// TODO add comments

import java.util.*;
import java.util.List;

public class Conway {
    private int generation = 1 ;
    private final int HASH_SIZE = (int) Math.pow(2, 14);
    private HashSet<Cell> world = new HashSet<>(HASH_SIZE);
    private HashMap<Cell, Integer> counts = new HashMap<>(HASH_SIZE);

    // Creates a random world
    public Conway(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell c = new Cell(x, y);
                // randomly decide if the given cell is alive
                if ( Math.random() > 0.9 ) {
                    addCell(c);
                }
            }
        }
    }

    public Conway(List<List<Integer>> initState) {
        for (List<Integer> cell : initState) {
            Cell c = new Cell(cell.get(0), cell.get(1));
            addCell(c);
        }
    }

    private void addCell(Cell c){
        world.add(c);
        // inc neighbors count
        for (Cell neighbor : c.getNeighbors()) {
            if (counts.containsKey(neighbor)) {
                counts.put(neighbor, counts.get(neighbor) + 1);
            }
            else {
                counts.put(neighbor, 1);
            }
        }
    }

    private void removeCell(Cell c) {
        world.remove(c);
        // dec neighbors count
        for (Cell neighbor : c.getNeighbors()) {
            if (counts.containsKey(neighbor) && counts.get(neighbor)-1 > 0) {
                counts.put(neighbor, counts.get(neighbor) - 1);
            }
            else counts.remove(neighbor);
        }
    }

    public void step() {
        generation++;

        List<Cell> toRemove = new ArrayList<>();
        List<Cell> toAdd = new ArrayList<>();

        // Handle live cells (rules 1-3)
        for (Cell c : world) {
            Integer n = counts.get(c);
            if (n == null || n < 2 || n > 3)
                toRemove.add(c);
        }
        // Handle dead cells (rule 4)
        for (Cell c : counts.keySet()) {
            Integer n = counts.get(c);
            if (n == 3 && !world.contains(c))
                toAdd.add(c);
        }
        // Remove all cells from toRemove
        for (Cell c : toRemove) {
            removeCell(c);
        }
        // Add all cells from toAdd
        for (Cell c : toAdd) {
            addCell(c);
        }
    }

    public int getGeneration() {
        return generation;
    }

    public boolean isAlive(int x, int y) {
        return world.contains(new Cell(x,y));
    }

    private class Cell {
        int x, y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Cell) {
                Cell o = (Cell) other;
                return o.x == x && o.y == y;
            }
            else return false;
        }

        @Override
        public int hashCode() {
            return x*3 + y*5;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ')';
        }

        public Cell[] getNeighbors() {
            return new Cell[]   {
                                new Cell(x - 1, y - 1),
                                new Cell(x - 1, y),
                                new Cell(x - 1, y + 1),
                                new Cell(x, y - 1),
                                new Cell(x, y + 1),
                                new Cell(x + 1, y - 1),
                                new Cell(x + 1, y),
                                new Cell(x + 1, y + 1),
                                };
        }
    }

}
