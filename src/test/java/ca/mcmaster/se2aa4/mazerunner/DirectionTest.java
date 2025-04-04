package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test; 
import ca.mcmaster.se2aa4.mazerunner.core.Direction;

public class DirectionTest {

    @Test
    public void testUp() {
        Direction direction = Direction.UP;
        
        assertEquals(Direction.DOWN, direction.moveOpp());
        assertEquals(Direction.LEFT, direction.moveLeft());
        assertEquals(Direction.RIGHT, direction.moveRight());
    }

    @Test
    public void testDown() {
        Direction direction = Direction.DOWN;

        assertEquals(Direction.UP, direction.moveOpp());
        assertEquals(Direction.LEFT, direction.moveRight());
        assertEquals(Direction.RIGHT, direction.moveLeft());
    }

    @Test
    public void testLeft() {
        Direction direction = Direction.LEFT;

        assertEquals(Direction.RIGHT, direction.moveOpp());
        assertEquals(Direction.DOWN, direction.moveLeft());
        assertEquals(Direction.UP, direction.moveRight());
    }

    @Test
    public void testRight() {
        Direction direction = Direction.RIGHT;

        assertEquals(Direction.LEFT, direction.moveOpp());
        assertEquals(Direction.UP, direction.moveLeft());
        assertEquals(Direction.DOWN, direction.moveRight());
    }

}
