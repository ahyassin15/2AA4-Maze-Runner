package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeRunner {

    private Stack<String> factorizedPath = new Stack<>();
    private Stack<String> canonicalPath = new Stack<>();
    private Stack<int[]> forkLocations = new Stack<>();

    private int[] currentPosition = new int[2];
    private int[] exitPosition = new int[2];

    private boolean isFacingNorth = false;
    private boolean isFacingSouth = false;
    private boolean isFacingEast = false;
    private boolean isFacingWest = true;

    private Maze mazeMap;

    public void addToPath(String direction) {
        canonicalPath.push(direction);
    }

    public void removePath() {
        canonicalPath.pop();
    }

    public void backtrackToFork() {
        while(currentPosition!=forkLocations.peek()){
            removePath();
        }

        forkLocations.pop();
        moveLeft();
    }

    public String[] getPath() {
        return canonicalPath.toArray(new String[0]);
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int[] newPosition) {
        currentPosition = newPosition;
    }

    public String[] getFactorizedPath(){

        factorizedPath.clear();
        for (String direction : canonicalPath) {
            factorizedPath.push(direction);
        }
        return factorizedPath.toArray(new String[0]);
    }

    public void moveForward() {
        if (isFacingNorth) {
            currentPosition[0]--;
        } else if (isFacingSouth) {
            currentPosition[0]++;
        } else if (isFacingEast) {
            currentPosition[1]++;
        } else if (isFacingWest) {
            currentPosition[1]--;
        }
        addToPath("F");
    }

    public void moveLeft() {
        if (isFacingNorth) {
            isFacingWest = true;
            isFacingNorth = false;
        } else if (isFacingSouth) {
            isFacingEast = true;
            isFacingSouth = false;
        } else if (isFacingEast) {
            isFacingNorth = true;
            isFacingEast = false;
        } else if (isFacingWest) {
            isFacingSouth = true;
            isFacingWest = false;
        }
        addToPath("L");
    }

    public void moveRight() {
        if (isFacingNorth) {
            isFacingEast = true;
            isFacingNorth = false;
        } else if (isFacingSouth) {
            isFacingWest = true;
            isFacingSouth = false;
        } else if (isFacingEast) {
            isFacingSouth = true;
            isFacingEast = false;
        } else if (isFacingWest) {
            isFacingNorth = true;
            isFacingWest = false;
        }
        addToPath("R");
    }

    public boolean checkDeadEnd(String[][] mazeArray, int row, int col) {
        int walls = 0;
        if(row>0){
            if(!isWall(mazeArray,row-1,col)){
                walls++;
            }
        }
        if(col>0){
            if(!isWall(mazeArray,row,col-1)){
                walls++;
            }
        }
        if(row<mazeArray.length-1){
            if(!isWall(mazeArray,row+1,col)){
                walls++;
            }
        }
        if(col<mazeArray[0].length-1){
            if(!isWall(mazeArray,row,col+1)){
                walls++;
            }
        }
        if(walls>2){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isWall(String[][] mazeArray, int row, int col) {
        if (mazeArray[row][col].equals("#")){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFinishPoint(String[][] mazeArray, int row, int col) {
        if(currentPosition == exitPosition){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFork(String[][] mazeArray, int row, int col) {
        int openPaths = 0;
        if(row>0){
            if(!isWall(mazeArray,row-1,col)){
                openPaths++;
            }
        }
        if(col>0){
            if(!isWall(mazeArray,row,col-1)){
                openPaths++;
            }
        }
        if(row<mazeArray.length-1){
            if(!isWall(mazeArray,row+1,col)){
                openPaths++;
            }
        }
        if(col<mazeArray[0].length-1){
            if(!isWall(mazeArray,row,col+1)){
                openPaths++;
            }
        }
        if(openPaths>2){
            return true;
        }
        else{
            return false;
        }
    }

}