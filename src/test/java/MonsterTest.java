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

public class MonsterTest {

    private Monster monster;
    private Screen screen;

    @BeforeEach
    public void setUp() {
        monster = new Monster(1, 1, 10);
        screen = Mockito.mock(Screen.class);
    }

    @Test
    public void testDraw() {
        monster.draw(null, screen);

        TextCharacter expectedCharacter = new TextCharacter('M', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        verify(screen).setCharacter(1, 1, expectedCharacter);
    }

    @Test
    public void testMove() {
        Position newPosition = monster.move();

        // Verificar se a posição do monstro mudou
        assertNotEquals(new Position(1, 1), newPosition);

        // Verificar se a nova posição está dentro do intervalo esperado
        assertTrue(newPosition.getX() >= 0 && newPosition.getX() <= 2);
        assertTrue(newPosition.getY() >= 0 && newPosition.getY() <= 2);
    }

    @Test
    public void testGetDamage() {
        assertEquals(10, monster.getDamage());
    }
}
