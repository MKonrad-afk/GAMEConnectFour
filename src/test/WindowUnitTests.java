package test;

import main.Window;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class WindowUnitTests {

    Window win;
    @BeforeEach
    public void preSetup(){
        try {
            System.loadLibrary("libUTP_Project");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            win = new Window();
        } catch (IOException e) {
            System.err.println("IOException encountered: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void horizonralWinCondition0Row(){
        win.clearBoard();
        win.addAfterAcepting();//first player first token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(1);//column go right
        win.addAfterAcepting();//first player second token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(1);//column go right
        win.addAfterAcepting();//first player third token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(1);//column go right
        win.addAfterAcepting();//first player fourth token
        assertTrue( "Horizontal win condition failed", win.checkIfWIN());
    }

    @Test
    public void horizontalWinCondition1Row(){
        win.clearBoard();
        //filling the 0th row
        for (int i =0; i< 7; i++){
            win.addAfterAcepting();
            win.changeTheColumn(1);
        }
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//first player first token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//first player second token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//first player third token
        win.addAfterAcepting();//second player on the top
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//first player fourth token
        assertTrue( "Horizontal win condition failed", win.checkIfWIN());
    }

    @Test
    public void verticalWinCondition0Column(){
        win.clearBoard();
        win.addAfterAcepting();//first player first token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player first token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player second token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player second token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player third token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player third token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player fourth token
        assertTrue( "Vertical win condition failed", win.checkIfWIN());
    }

    @Test
    public void verticalWinCondition1Column(){
        win.clearBoard();
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//first player first token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player first token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player second token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player second token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player third token
        win.changeTheColumn(1);// go right
        win.addAfterAcepting();//second player third token
        win.changeTheColumn(0);// go left
        win.addAfterAcepting();//first player fourth token
        assertTrue( "Vertical win condition failed", win.checkIfWIN());
    }
    @Test
    public void diagonalWinCondition_Right(){
        win.clearBoard();
        win.addAfterAcepting();//first player first token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player first token
        win.addAfterAcepting();//first player second token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player second token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//first player third token
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//second player third token
        win.addAfterAcepting();//first player fourth token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player fourth token
        win.addAfterAcepting();//first player fifth token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player fifth token
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//first player sixth token
        assertTrue( "Diagonal Condition to the right win condition failed"
                , win.checkIfWIN());
    }

    @Test
    public void diagonalWinCondition_Left(){
        win.clearBoard();
        win.addAfterAcepting();//first player first token
        win.addAfterAcepting();//second player first token
        win.addAfterAcepting();//first player second token
        win.addAfterAcepting();//second player second token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//first player third token
        win.addAfterAcepting();//second player third token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//first player fourth token
        win.changeTheColumn(0);//go left
        win.addAfterAcepting();//second player forth token
        win.addAfterAcepting();//first player fifth token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player sixth token
        win.addAfterAcepting();//first player sixth token
        win.changeTheColumn(1);//go right
        win.addAfterAcepting();//second player eight token
        assertTrue( "Diagonal Condition to the left win condition failed"
                , win.checkIfWIN());
    }

    @Test
    public void noWinConditionEmptyMaze(){
        win.clearBoard();
        assertFalse( "No win condition failed when Maze is Empty", win.checkIfWIN());

    }

    @Test
    public void noWinConditionMazeFilledWithNoWinTokens(){
        win.clearBoard();
        for (int i =0; i< 3; i++){
            int tempDirection = 1;
            for (int j = 0; j<7;j++) {
                win.addAfterAcepting();
                win.changeTheColumn(tempDirection);
            }
            if (tempDirection==1){
                tempDirection=0;
            }
            else {
                tempDirection=1;
            }
        }
        assertFalse( "No win condition failed when Maze is Filled with no win tokens", win.checkIfWIN());
    }

    @Test
    public void noTurnSwitchingWhenClickNotInTheOvalTokenActiveFiled(){
        win.clearBoard();
        int tempInital =  win.getCurrentColor();
        win.handleClick(0,0);
        assertFalse("No switching after clicking outside of the oval active field FAILED", tempInital != win.getCurrentColor());

    }

    @Test
    public void turnSwitchingWhenClickingInsideOfTheOval(){
        win.clearBoard();
        int tempInital =  win.getCurrentColor();
        win.handleClick(100,150); // size taken from random oval active filed in the maze
        assertTrue("Switching after clicking inside of the oval active field FAILED", tempInital!= win.getCurrentColor());
    }

    @Test
    public void clearBoardWhenBoardIsEmpty(){
        win.clearBoard();
        assertEquals("Clearing Board when it is empty Failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000", win.getBoardInTheStringRepresentation());

    }

    @Test
    public void clearBoardWhenIsNotEmpty(){
        for (int i =0; i< 7; i++){
            win.addAfterAcepting();
            win.changeTheColumn(1);
        }
        win.clearBoard();
        assertEquals("Clearing Board when it is not empty Failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000", win.getBoardInTheStringRepresentation());
    }

    @Test
    public void tokenPlacementAtDifferentPositionsByMouse(){
        win.clearBoard();
        win.handleClick(100,150);
        win.handleClick(200,150);
        assertEquals("Token Placement At different Positions by mouse failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "1200000", win.getBoardInTheStringRepresentation());
    }

    @Test
    public void tokenPlacementAtTheSamePositionByMouse(){
        win.clearBoard();
        win.handleClick(100,150);
        win.handleClick(100,150);
        assertEquals("Token Placement At the same Position by mouse failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "2000000"+
                        "1000000", win.getBoardInTheStringRepresentation());
    }

    @Test
    public void tokenPlacementAtDifferentPositionsByKeyboard(){
        win.clearBoard();
        win.addAfterAcepting();
        win.changeTheColumn(1);
        win.addAfterAcepting();
        assertEquals("Token Placement At different Positions by keyboard failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "1200000", win.getBoardInTheStringRepresentation());
    }

    @Test
    public void tokenPlacementAtTheSamePositionByKeyboard(){
        win.clearBoard();
        win.addAfterAcepting();
        win.addAfterAcepting();
        assertEquals("Token Placement At same Position by keyboard failed",
                "0000000" +
                        "0000000"+
                        "0000000"+
                        "0000000"+
                        "2000000"+
                        "1000000", win.getBoardInTheStringRepresentation());
    }


}
