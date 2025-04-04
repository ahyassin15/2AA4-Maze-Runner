package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 
import ca.mcmaster.se2aa4.mazerunner.core.Direction;
import ca.mcmaster.se2aa4.mazerunner.core.Position;

public class PositionTest {
    
    private Position testPosition;
 
    @BeforeEach
    public void setUpPosition() {
        testPosition = new Position(2, 3);
    }
    
    @Test
    public void testInitializationPosition() {
        assertEquals(2, testPosition.getX());
        assertEquals(3, testPosition.getY());
    }
    
    @Test
    public void testSetPosition() {
        testPosition.setX(7);
        testPosition.setY(4);
        assertEquals(7, testPosition.getX());
        assertEquals(4, testPosition.getY());
    }
    
    @Test
    public void testWithinBounds() {
        Position mazeSize = new Position(8, 6);
    
        //within bounds of maze size
        assertTrue(testPosition.checkWithinBounds(mazeSize));
    
        //x is out of bounds
        testPosition.setX(9);
        assertFalse(testPosition.checkWithinBounds(mazeSize));
    
        //y is out of bounds
        testPosition.setX(2);
        testPosition.setY(7);
        assertFalse(testPosition.checkWithinBounds(mazeSize));
    
        //x is negative
        testPosition.setY(3);
        testPosition.setX(-2);
        assertFalse(testPosition.checkWithinBounds(mazeSize));
    
        //y is negative
        testPosition.setX(2);
        testPosition.setY(-5);
        assertFalse(testPosition.checkWithinBounds(mazeSize));
    
        //centered & valid position
        testPosition.setX(4);
        testPosition.setY(2);
        assertTrue(testPosition.checkWithinBounds(mazeSize));
    }
    
    @Test
    public void testLeftMove() {
        testPosition = testPosition.move(Direction.LEFT);
        assertEquals(1, testPosition.getX());
        assertEquals(3, testPosition.getY());
    }
    
    @Test
    public void testRightMove() {
        testPosition = testPosition.move(Direction.RIGHT);
        assertEquals(3, testPosition.getX());
        assertEquals(3, testPosition.getY());
    }
    
    @Test
    public void testUpMove() {
        testPosition = testPosition.move(Direction.UP);
        assertEquals(2, testPosition.getY());
        assertEquals(2, testPosition.getX());
    }
    
    @Test
    public void testDownMove() {
        testPosition = testPosition.move(Direction.DOWN);
        assertEquals(2, testPosition.getY());
        assertEquals(4, testPosition.getX());
    }
}