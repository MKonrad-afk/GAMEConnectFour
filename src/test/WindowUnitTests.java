package test;

import main.Window;
import org.junit.jupiter.api.*;

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

    }

    @Test
    public void diagonalWinCondition_Left(){

    }

    @Test
    public void noWinCondition(){

    }

    @Test
    public void turnSwitching(){

    }

    @Test
    public void clearBoard(){

    }


    @Test
    public void tokenPlacementAtDifferentPositions(){

    }

    @Test
    public void tokenPlacementAtTheSamePosition(){

    }


}
