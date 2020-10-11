import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife extends JPanel {

	static int cellSize = 5;
	static int height = 1000;
	static int width = 1000;
	static Cell grid[][] = new Cell[width / cellSize][height / cellSize];

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Game of life");
		GameOfLife app = new GameOfLife();
		frame.add(app);
		frame.setSize(514, 537);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		Random rnd = new Random();

		for (int y = 0; y < height / cellSize; y++) {
			for (int x = 0; x < width / cellSize; x++) {
				grid[x][y] = new Cell(x, y, rnd.nextBoolean());
			}
		}

		while (true) {
			app.repaint();
			Thread.sleep(50);
			grid = update(grid);
		}

	}

	public static double map(int x, double in_min, double in_max, double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	@Override
	public void paint(Graphics g) {
		for (int y = 0; y < height / cellSize; y++) {
			for (int x = 0; x < width / cellSize; x++) {
				Color color = (grid[x][y].alive) ? new Color(0, 0, 0) : new Color(255, 255, 255);

				g.setColor(color);
				g.fillRect(cellSize * x, cellSize * y, cellSize, cellSize);
				;
			}
		}

	}

	public static Cell[][] update(Cell[][] grid) {
		Cell newgrid[][] = new Cell[width / cellSize][height / cellSize];
		for (int y = 0; y < height / cellSize; y++) {
			for (int x = 0; x < width / cellSize; x++) {
				if (grid[x][y].alive == false && grid[x][y].countNeighbours(grid) == 3) {
					newgrid[x][y] = new Cell(grid[x][y].x, grid[x][y].y, true);
				} else if (grid[x][y].alive == true && grid[x][y].countNeighbours(grid) == 2
						|| grid[x][y].countNeighbours(grid) == 3) {
					newgrid[x][y] = new Cell(grid[x][y].x, grid[x][y].y, true);
				} else {
					newgrid[x][y] = new Cell(grid[x][y].x, grid[x][y].y, false);
				}

			}
		}
		grid = null;

		return newgrid;

	}

}
