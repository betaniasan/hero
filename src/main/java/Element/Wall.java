package Element;

import Element.Element;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.Collections;

public class Wall extends Element {

    public Wall(int x, int y) {
        super(x, y);
    }
    @Override
    public void draw(TextGraphics graphics, Screen screen) {

        TextCharacter wallCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        TextCharacter wallBackground = new TextCharacter(' ', TextColor.Factory.fromString("#999999"), TextColor.ANSI.DEFAULT);
        TextCharacter boldCharacter = new TextCharacter('#', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);
        boldCharacter = boldCharacter.withModifiers(Collections.singleton(SGR.BOLD));
        int x = position.getX();
        int y = position.getY();

        int x2 = x * 2;
        int y2 = y * 2;

        screen.setCharacter(x, y, wallCharacter);
        graphics.setCharacter(x2, y2, wallBackground);
        graphics.setCharacter(x2 + 1, y2, wallBackground);
        graphics.setCharacter(x2, y2 + 1, wallBackground);
        graphics.setCharacter(x2 + 1, y2 + 1, wallBackground);

        screen.setCharacter(x, y, boldCharacter);
        graphics.setCharacter(x2 + 1, y2, boldCharacter);
        graphics.setCharacter(x2, y2 + 1, boldCharacter);
        graphics.setCharacter(x2 + 1, y2 + 1, boldCharacter);
    }

    public Position getPosition() {
        return position;
    }
}
