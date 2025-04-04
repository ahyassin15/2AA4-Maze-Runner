package ca.mcmaster.se2aa4.mazerunner;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.mazerunner.maze.Maze;
import ca.mcmaster.se2aa4.mazerunner.maze.MazePath;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeImporter;
import ca.mcmaster.se2aa4.mazerunner.maze.MazeRunner;
import ca.mcmaster.se2aa4.mazerunner.solver.MazeSolver;
import ca.mcmaster.se2aa4.mazerunner.solver.RightHandSolver;

public class PathTest {
    
    private static final Logger logger = LogManager.getLogger();

    @Test
    public void testPathEquivalence() throws Exception {

        MazeSolver solver = new RightHandSolver();

        File directory = new File("examples");
        File[] mazes = directory.listFiles();

        for (File mazeFile : mazes) {
            Maze maze = MazeImporter.scanMaze(mazeFile.getAbsolutePath());
            logger.info("Maze Dimensions: {}\n", maze.getDimensions());

            String factoredPath = solver.solveMaze(maze);
            logger.info("Factored Path: {}", factoredPath);

            String canonicalPath = MazeRunner.toCanonicalPath(factoredPath);
            logger.info("Canonical Path: {}", canonicalPath);

            String newFactoredPath = MazeRunner.toFactoredPath(canonicalPath);
            logger.info("New Factored Path: {}", newFactoredPath);

            assertEquals(factoredPath, newFactoredPath);
        }
    }

    @Test
    public void testToCanonical() {
        String factoredPath = "6F 2L 4R 3F";
        String noSpaceFactPath = "6F2L4R3F";
        String canonicalPath = "FFFFFF LL RRRR FFF";
        String noSpaceCanPath = "FFFFFFLLRRRRFFF";

        MazePath path = new MazePath(factoredPath);
 
        assertEquals(factoredPath, path.toFactoredForm(true));
        assertEquals(noSpaceFactPath, path.toFactoredForm());
        assertEquals(canonicalPath, path.toCanonicalForm(true));
        assertEquals(noSpaceCanPath, path.toCanonicalForm());
    }
 
    @Test
    public void testToFactored() {
        String canonicalPath = "FFFFFF LL RRRR FFF";
        String noSpaceCanPath = "FFFFFFLLRRRRFFF";
        String factoredPath = "6F 2L 4R 3F";
        String noSpaceFactPath = "6F2L4R3F";
 
        MazePath path = new MazePath(canonicalPath);

        assertEquals(factoredPath, path.toFactoredForm(true));
        assertEquals(noSpaceFactPath, path.toFactoredForm());
        assertEquals(canonicalPath, path.toCanonicalForm(true));
        assertEquals(noSpaceCanPath, path.toCanonicalForm());
    }
}
