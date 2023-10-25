import Element.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void testGetX() {
        Position position = new Position(1, 2);
        assertEquals(1, position.getX());
    }

    @Test
    public void testGetY() {
        Position position = new Position(1, 2);
        assertEquals(2, position.getY());
    }

    @Test
    public void testEquals() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);
        Position position3 = new Position(2, 1);

        assertTrue(position1.equals(position2));
        assertFalse(position1.equals(position3));
    }

    @Test
    public void testHashCode() {
        Position position1 = new Position(1, 2);
        Position position2 = new Position(1, 2);

        assertEquals(position1.hashCode(), position2.hashCode());
    }
}
