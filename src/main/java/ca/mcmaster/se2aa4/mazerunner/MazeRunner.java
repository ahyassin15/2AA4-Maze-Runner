package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    Stack<String> factorizedPath = new Stack<>();
    Stack<String> canonizedPath = new Stack<>();
    Stack<int[]> forkLocations = new Stack<>();

    boolean facingNorth = false;
    boolean facingSouth = false;
    boolean facingEast = false;
    boolean facingWest = true;

    public void addToPath(){
        
    }

    public void removeFromPath(){

    }

    public void backtrackToFork(){

    }

    public Stack getPath(){

    }

    public int[] getPosition(){

    }

    public void setPosition(){

    }

    public void moveForward(){

    }

    public void moveLeft(){

    }

    public void moveRight(){

    }

    public boolean checkDeadEnd(int row, int col){

    }

    public boolean checkWall(int row, int col){

    }

    public boolean checkFinish(int row, int col){
        
    }

}