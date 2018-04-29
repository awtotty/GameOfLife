// TODO add comments
// TODO add file opener to allow for initial state via txt file

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConwayGUI extends JComponent {
    private static int boardSize = 1080;
    private static final int CELL_DIM = 5;
    private static final int WINDOW_SIZE = boardSize *CELL_DIM;
    private static final int FPS = 500;

    private static Conway game = new Conway(boardSize);

    public ConwayGUI() {
        setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < boardSize; x++)
            for (int y = 0; y < boardSize; y++)
                if (game.isAlive(x,y))
                    g.fillRect(x*CELL_DIM, y*CELL_DIM, CELL_DIM, CELL_DIM);
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        // Gen counter
        JPanel genCounter = new JPanel();
        JLabel genLabel = new JLabel("Gen: " + game.getGeneration());
        genCounter.add(genLabel);
        genCounter.setBackground(Color.GRAY);
        genCounter.setOpaque(true);
        genCounter.setBounds(0,0, 55,28);

        // Grid
        JPanel grid = new JPanel();
        grid.add(new ConwayGUI());
        grid.setOpaque(true);
        grid.setBounds(0,0, WINDOW_SIZE, WINDOW_SIZE);

        // Layered panel
        JLayeredPane lpane = new JLayeredPane();
        lpane.setBounds(0,0, WINDOW_SIZE, WINDOW_SIZE);
        lpane.setPreferredSize(new Dimension(WINDOW_SIZE, WINDOW_SIZE));
        lpane.add(grid, 0, 0);
        lpane.add(genCounter, 1, 0);

        frame.add(lpane, BorderLayout.CENTER);

        Timer timer = new Timer(FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // update variables
                game.step();
                genLabel.setText("Gen: " + game.getGeneration());
                lpane.repaint();
            }
        });
        timer.start();

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }



}
