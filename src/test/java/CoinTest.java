import Element.Coin;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class CoinTest {

    private Coin coin;
    private Screen screen;

    @BeforeEach
    public void setUp() {
        coin = new Coin(1, 1);
        screen = Mockito.mock(Screen.class);
    }

    @Test
    public void testDraw() {
        coin.draw(null, screen);

        TextCharacter expectedCharacter = new TextCharacter('C', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT);
        verify(screen).setCharacter(1, 1, expectedCharacter);
    }
}
