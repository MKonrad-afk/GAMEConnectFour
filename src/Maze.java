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
        while(row < maze.length-1 && maze[row+1][column]==0){
            row++;
        }
        maze[row][column] = player;
    }

    public int[][] getMaze(){
        return maze;
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



    //Methods to check if player win
    private boolean checkHorizonral(){
        for(int row =0; row< maze.length; row++ ){
            for(int column = 0; column < maze[row].length-3;column++){
                int value = maze[row][column];
                if (value !=0 && value == maze[row][column+1]
                                && value == maze[row][column+2]
                                    && value == maze[row][column+3]){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertival(){
        for (int column =0; column< maze[0].length;column++){
            for (int row =0; row< maze.length-3;row++){
                int value = maze[row][column];
                if (value!=0 && value== maze[row+1][column]
                                && value == maze[row+2][column]
                                    && value == maze[row+3][column]){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(){
        //diagonal that go down-right
        for (int row =0; row< maze.length-3;row++){
            for (int column =0; column< maze[row].length-3;column++){
                int value = maze[row][column];
                if (value!=0 && value == maze[row+1][column+1]
                                && value == maze[row+2][column+2]
                                    && value == maze[row+3][column+3]){
                    return true;
                }
            }
        }

        //diagonal that go down-left
        for (int row =0 ; row < maze.length-3;row++){
            for (int column = 3; column< maze[row].length;column++){
                int value = maze[row][column];
                if (value!=0 && value == maze[row+1][column-1]
                                && value == maze[row+2][column-2]
                                    && value == maze[row+3][column-3]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfWIN(){
        return checkHorizonral() || checkDiagonal() || checkVertival();
    }
}