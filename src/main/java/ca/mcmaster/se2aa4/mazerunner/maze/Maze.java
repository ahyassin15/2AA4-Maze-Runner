package ca.mcmaster.se2aa4.mazerunner.maze;

import ca.mcmaster.se2aa4.mazerunner.core.MazeCell;
import ca.mcmaster.se2aa4.mazerunner.core.Position;

public class Maze {

    //2D array storing maze cells
    private MazeCell[][] mazeGrid;

    //Constructor that initializes the maze from a character array
    public Maze(char[][] maze) {
        
        this.mazeGrid = new MazeCell[maze.length][maze[0].length];

        //Iterate through each row and col, assigning the correct type of cell (wall or passage)
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                this.mazeGrid[row][col] = MazeCell.fromChar(maze[row][col]);
            }

            //Fill any remaining space in the row with PASSAGE cells
            for (int col = maze[row].length; col < this.mazeGrid[0].length; col++) {
                this.mazeGrid[row][col] = MazeCell.PASSAGE;
            }
        }
    }

    //Constructor that initializes the maze array from an existing grid of walls and passages
    public Maze(MazeCell[][] maze) {
        this.mazeGrid = maze;
    }

    //Gets the cell type at a given position
    public MazeCell getCell(Position position) {
        return mazeGrid[position.getY()][position.getX()];
    }

    //Finds the entry point (first column with a passage)
    public Position getEntryPoint() {
        for (int col = 0; col < mazeGrid.length; col++) {
            if (!getCell(new Position(0, col)).checkWall()) {
                return new Position(0, col);
            }
        }
        return null; //Return null if no entry point found
    }

    //Finds the exit point from position (last column and not a wall)
    public boolean checkExitPoint(Position position) {
        return (position.getX() == mazeGrid[0].length - 1) && !getCell(position).checkWall(); 
    }

    //Prints the maze to the console
    public void displayMaze() {
        for (int row = 0; row < mazeGrid.length; row++) {
            for (int col = 0; col < mazeGrid[row].length; col++) {
                if (mazeGrid[row][col] == MazeCell.WALL) {
                    System.out.print("WALL ");
                } else {
                    System.out.print("PASS ");
                }
            }
            System.out.println(); //Move to the next line for the next row
        }
    }
}