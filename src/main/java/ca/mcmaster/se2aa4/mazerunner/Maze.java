package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private static final Logger logger = LogManager.getLogger();

    String[][] mazeGrid;

    Maze(String[][] mazeGrid){
        this.mazeGrid = mazeGrid;
    }

    public String[][] getMazeArray(){
        return mazeGrid;
    }

    public void setMazeArray(String[][] mazeGrid){
        this.mazeGrid = mazeGrid;
    }

    public void displayMaze(){

        for (int i = 0; i < mazeGrid.length; i++){
            for (int j = 0; j < mazeGrid[i].length; j++){
                logger.info(mazeGrid[i][j]);
            }
            logger.info(System.lineSeparator());
        }
        
    }

    public int[] getEntryPoint(){

    }

    public int[] getExitPoint(){

    }
}
