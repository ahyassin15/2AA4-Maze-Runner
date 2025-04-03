package ca.mcmaster.se2aa4.mazerunner;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
 
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeImporter;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeRunner;
import ca.mcmaster.se2aa4.mazerunner.solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.solver.RightHandSolver;

public class PathTest {
    
    private MazeSolver solver;
    private Maze maze;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void initialize() throws Exception {
        maze = MazeImporter.scanMaze("examples/giant.maz.txt");
        solver = new RightHandSolver();
        logger.info("Maze Dimensions: {}\n", maze.getDimensions());
    }

    @Test
    public void testPathEquivalence() {
        String factoredPath = solver.solveMaze(maze);
        logger.info("Factored Path: {}", factoredPath);

        String canonicalPath = MazeRunner.toCanonicalPath(factoredPath);
        logger.info("Canonical Path: {}", canonicalPath);

        String newFactoredPath = MazeRunner.toFactoredPath(canonicalPath);
        logger.info("New Factored Path: {}", newFactoredPath);

        assertEquals(factoredPath, newFactoredPath);
    }
}
