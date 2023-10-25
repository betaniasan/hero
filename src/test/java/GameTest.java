import Element.Position;
import Main.Game;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    private Game game;
    private Screen screen;

    @BeforeEach
    public void setUp() throws IOException {
        game = new Game();
        screen = Mockito.mock(Screen.class);
    }

    @Test
    public void testProcessKey() throws IOException {
        KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
        Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);

        game.processKey(keyStroke);

        // Verifique se a posição do herói mudou após o processamento da tecla
        assertEquals(new Position(10, 9), game.getArena().getHero().getPosition());
    }

    // Adicione mais testes conforme necessário para os outros métodos na classe Game
}
