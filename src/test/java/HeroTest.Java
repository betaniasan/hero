import Element.Hero;
import Element.Monster;
import Element.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class HeroTest {

    private Hero hero;
    private Screen screen;
    private Monster monster;

    @BeforeEach
    public void setUp() {
        hero = new Hero(1, 1, 100);
        screen = Mockito.mock(Screen.class);
        monster = Mockito.mock(Monster.class);
        Mockito.when(monster.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(monster.getDamage()).thenReturn(10);
    }

    @Test
    public void testDraw() {
        hero.draw(null, screen);

        TextCharacter expectedCharacter = new TextCharacter('H', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        verify(screen).setCharacter(1, 1, expectedCharacter);
    }

    @Test
    public void testMoveUp() {
        Position newPosition = hero.moveUp();
        assertEquals(new Position(1, 0), newPosition);
    }

    @Test
    public void testMoveDown() {
        Position newPosition = hero.moveDown();
        assertEquals(new Position(1, 2), newPosition);
    }

    @Test
    public void testMoveLeft() {
        Position newPosition = hero.moveLeft();
        assertEquals(new Position(0, 1), newPosition);
    }

    @Test
    public void testMoveRight() {
        Position newPosition = hero.moveRight();
        assertEquals(new Position(2, 1), newPosition);
    }

    @Test
    public void testCollidesWithMonster() {
        boolean result = hero.collidesWithMonster(monster);
        assertTrue(result);
        assertEquals(90, hero.getEnergy());
    }
}
