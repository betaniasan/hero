package Element;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.util.Random;

public class Monster extends Element {


    private int damage;
    public Monster(int x, int y, int damage) {
        super(x, y);
        this.damage = damage;
    }

    @Override
    public void draw(TextGraphics graphics, Screen screen) {
        TextCharacter monsterCharacter = new TextCharacter('M', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT);

        int x2 = position.getX() * 2;
        int y2 = position.getY() * 2;

        screen.setCharacter(position.getX(), position.getY(), monsterCharacter);
    }

    public Position move() {
        Random random = new Random();
        int dx = random.nextInt(3) - 1;
        int dy = random.nextInt(3) - 1;

        // Nova posição após o movimento
        int newX = position.getX() + dx;
        int newY = position.getY() + dy;

        return new Position(newX, newY);
    }
    public int getDamage() {
        return damage;
    }
}
