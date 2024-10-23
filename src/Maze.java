//should be converted to C++
public class Maze {
    private int[][] maze;
    // maze states:
    //0-> free
    //1-> 1st player
    //2-> 2nd player

    public Maze() {
       maze = new int[6][7];
    }
    public void putBall (int player, int row, int column){
        while(maze[row][column]==0 && !(maze.length-1==row)){
            row++;
        }
        maze[row][column] = player;
    }

    public int[][] getMaze(){
        return maze;
    }
    public boolean checkIfWinEachRow(){
        for (int i =0;i< maze.length;i++){
            for (int j = 0;j<maze[i].length-4;j++){
                if(maze[i][j]==maze[i][j++]&&
                        maze[i][j++]==maze[i][j+2]&&
                            maze[i][j+2]==maze[i][j+3]&&
                                maze[i][j+3]==maze[i][j+4]){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        String mazeString = "-------\n";
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                mazeString += maze[i][j];
            }
            mazeString += "\n";
        }
        return mazeString;
    }
}