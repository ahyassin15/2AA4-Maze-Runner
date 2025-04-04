package ca.mcmaster.se2aa4.mazerunner;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeImporter;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeRunner;
import ca.mcmaster.se2aa4.mazerunner.solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.solver.RightHandSolver;
import java.io.File;

public class MazeTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    public void testPathEquivalence() throws Exception {

        MazeSolver solver = new RightHandSolver();
        File directory = new File("examples");
        File[] mazes = directory.listFiles();

        for (File mazeFile : mazes) {
            Maze maze = MazeImporter.scanMaze(mazeFile.getAbsolutePath());
            logger.info("Maze Dimensions: {}\n", maze.getDimensions());

            MazeRunner runner = new MazeRunner(maze);

            String path = solver.solveMaze(maze);
            logger.info("Path: {}", path);
            assertTrue(runner.verifyPath(maze, path));
        }
    }
}