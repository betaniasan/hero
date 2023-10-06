package a042807;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.*;

    public class Arena {
        private int width;
        private int height;
        private boolean isRunning = true;
        Hero hero;

        private List<Wall> walls;

        public Arena(int width, int height) {
            hero = new Hero(10, 10);
            this.walls = createWalls();
            this.width = width;
            this.height = height;
        }

        public void processKey(KeyStroke key, Screen screen) throws IOException {
            if (key.getKeyType() == KeyType.EOF) {
                isRunning = false;
                return;
            }
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
            }

            Position newPosition = hero.getPosition();

            if (key.getKeyType() == KeyType.ArrowUp) {
                newPosition = hero.moveUp();
            } else if (key.getKeyType() == KeyType.ArrowDown) {
                newPosition = hero.moveDown();
            } else if (key.getKeyType() == KeyType.ArrowLeft) {
                newPosition = hero.moveLeft();
            } else if (key.getKeyType() == KeyType.ArrowRight) {
                newPosition = hero.moveRight();
            }

            if (canHeroMove(newPosition)) {
                moveHero(newPosition);
            }
        }

        public void draw(TextGraphics textGraphics, Screen screen) throws IOException {
            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height *2),' ');
            for (Wall wall : walls)
                wall.draw(graphics, screen);
            hero.Draw(screen.newTextGraphics(), screen);
        }

        private void moveHero(Position position) {
            hero.setPosition(position);
        }

        private List<Wall> createWalls() {
            List<Wall> walls = new ArrayList<>();
            for (int c = 0; c < width; c++) {
                walls.add(new Wall(c, 0));
                walls.add(new Wall(c, height - 1));
            }
            for (int r = 1; r < height - 1; r++) {
                walls.add(new Wall(0, r));
                walls.add(new Wall(width - 1, r));
            }
            return walls;
        }
        public boolean canHeroMove(Position position) {
            for (Wall wall : walls) {
                if (wall.getPosition().equals(position)) {
                    return false;
                }
            }
            return position.getX() >= 0 && position.getX() < width &&
                    position.getY() >= 0 && position.getY() < height;
        }
        public boolean isRunning() {
            return isRunning;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }



        public void setRunning(boolean running) {
            isRunning = running;
        }


    }


