//should be converted to C++

public class Maze {
    private int[][] maze;
    private boolean[][] visited;

    public Maze() {
        maze = new int[6][7];
    }
    public boolean activeMaze(int x, int y) {
        if (visited[x][y])
            return true;
        return false;
    }
    @Override
    public String toString() {
        String mazeString = "";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                mazeString += maze[i][j];
            }
            mazeString += "\n";
        }
        return mazeString;
    }

}
