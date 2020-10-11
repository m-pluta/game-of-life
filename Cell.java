public class Cell {
	int x, y;
	boolean alive = false;

	public Cell(int x2, int y2, boolean alive) {
		this.x = x2;
		this.y = y2;
		this.alive = alive;
	}

//	public void desc(Cell[][] grid) {
//		System.out.println("Pixel at " + this.x + ", " + this.y + " is " + this.alive + " with " + countNeighbours(grid)
//				+ " neighbours.");
//	}

	public int countNeighbours(Cell[][] grid) {
		int aliveNeighbours = 0;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (this.x + j > -1 && this.x + j < grid.length && this.y + i > -1 && this.y + i < grid[0].length) {
					if (grid[this.x + j][this.y + i].alive) {
						aliveNeighbours++;
					}
				}
			}
		}
		if (grid[this.x][this.y].alive) {
			aliveNeighbours--;
		}
		return aliveNeighbours;
	}

}
