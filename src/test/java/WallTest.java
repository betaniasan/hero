import Element.Position;
import Element.Wall;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class WallTest {

    private Wall wall;
    private Screen screen;

    @BeforeEach
    public void setUp() {
        wall = new Wall(1, 1);
        screen = Mockito.mock(Screen.class);
    }

    @Test
    public void testDraw() {
        wall.draw(null, screen);

        TextCharacter expectedCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        verify(screen).setCharacter(1, 1, expectedCharacter);
    }

    @Test
    public void testGetPosition() {
        assertEquals(new Position(1, 1), wall.getPosition());
    }
}
