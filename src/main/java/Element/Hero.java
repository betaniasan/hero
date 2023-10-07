package Element;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element {
    private int energy;
    public Hero(int x, int y, int initialEnergy ){
        super(x, y);
        energy = initialEnergy;
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() -1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() +1, position.getY());
    }
   @Override
    public void draw(TextGraphics graphics, Screen screen){
        screen.setCharacter(position.getX(), position.getY(), new TextCharacter('X', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX() , position.getY() ), "\\/");
        graphics.putString(new TerminalPosition(position.getX() , position.getY() ), "/\\");
    }

    public boolean collidesWithMonster(Monster monster) {
        if (position.equals(monster.getPosition())) {
            energy -= monster.getDamage();
            return true;
        }
        return false;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public int getEnergy() {
        return energy;
    }
}
