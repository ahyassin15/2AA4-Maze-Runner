package ca.mcmaster.se2aa4.mazerunner;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeImporter;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeRunner;
import ca.mcmaster.se2aa4.mazerunner.solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.solver.RightHandSolver;

public class MazeTest {
    
    private MazeSolver solver;
    private MazeRunner runner;
    private Maze maze;

    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void initialize() throws Exception {
        maze = MazeImporter.scanMaze("examples/giant.maz.txt");
        solver = new RightHandSolver();
        runner = new MazeRunner(maze);
        logger.info("Maze Dimensions: {}\n", maze.getDimensions());
    }

    @Test
    public void testPathEquivalence() {
        String path = solver.solveMaze(maze);
        logger.info("Path:", path);
        assertTrue(runner.verifyPath(maze, path));
    }

    @Test
    public void testToCanonical() {
        String factoredPath = "6F 2L 4R 3F";
        String canonicalPath = MazeRunner.toCanonicalPath(factoredPath);
        assertEquals("FFFFFFLLRRRRFFF", canonicalPath);
 
        factoredPath = "5F 3R 2F";
        canonicalPath = MazeRunner.toCanonicalPath(factoredPath);
        assertEquals("FFFFFRRRFF", canonicalPath);
    }
 
    @Test
    public void testToFactored() {
        String canonicalPath = "FFFFFFLLRRRRFFF";
        String factoredPath = MazeRunner.toFactoredPath(canonicalPath);
        assertEquals("6F2L4R3F", factoredPath);
 
        canonicalPath = "FFFFFRRRFF";
        factoredPath = MazeRunner.toFactoredPath(canonicalPath);
        assertEquals("5F3R2F", factoredPath);
    }
}