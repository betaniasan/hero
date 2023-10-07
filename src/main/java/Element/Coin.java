package Element;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Coin extends Element {

    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {
        TextCharacter coinCharacter = new TextCharacter('C', TextColor.ANSI.YELLOW, TextColor.ANSI.DEFAULT);

        int x2 = position.getX() * 2;
        int y2 = position.getY() * 2;

        screen.setCharacter(position.getX(), position.getY(), coinCharacter);
    }

}
