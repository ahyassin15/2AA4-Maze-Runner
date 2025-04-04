package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ca.mcmaster.se2aa4.mazerunner.core.MazeCell;

public class CellTest {
    
    @Test
    public void testWall() {
        MazeCell wallCell = MazeCell.WALL;
        
        assertTrue(wallCell.checkWall());
        assertEquals(wallCell, MazeCell.fromChar('#'));
    }

    @Test
    public void testPassage() {
        MazeCell passageCell = MazeCell.PASSAGE;

        assertFalse(passageCell.checkWall());
        assertEquals(passageCell, MazeCell.fromChar(' '));
    }

}
