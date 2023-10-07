package Main;
import Element.*;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Arena {
    private boolean gameOver = false;
    private int coinCount = 0;
    private int width;
    private int height;
    private boolean isRunning = true;
    Hero hero;

    private List<Wall> walls = new ArrayList<Wall>();
    private List<Coin> coins = new ArrayList<Coin>();
    private List<Monster> monsters = new ArrayList<Monster>();

    public Arena(int width, int height) {
        hero = new Hero(10, 10,3);
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if (gameOver) {
            screen.close();
        } else {
            Position newPosition = hero.getPosition();
            if (key.getKeyType() == KeyType.EOF) {
                isRunning = false;
            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                screen.close();
            } else if (key.getKeyType() == KeyType.ArrowUp) {
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
                retrieveCoins();
                verifyMonsterCollisions();
            }
        }
    }
    public void addWall(Wall wall) {
        walls.add(wall);
    }

    public void setHeroPosition(Position position) {
        hero.setPosition(position);
    }

    public void addCoin(Coin coin) {
        coins.add(coin);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // Criar 3 monstros, você pode ajustar esse número
            int randomX = random.nextInt(width - 2) + 1;
            int randomY = random.nextInt(height - 2) + 1;
            Monster monster = new Monster(randomX, randomY, 1);
            Monster monster1 = new RandomMonster(randomX, randomY,2);
            monsters.add(monster);
            monsters.add(monster1);
        }
        return monsters;
    }
    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position newPosition = monster.move();
            if (canMonsterMove(newPosition, walls, coins, hero)) {
                monster.setPosition(newPosition);
            }
        }
    }
    public boolean canMonsterMove(Position newPosition, List<Wall> walls, List<Coin> coins, Hero hero) {
        // Verifique se a nova posição está dentro dos limites da arena
        if (newPosition.getX() < 0 || newPosition.getX() >= width || newPosition.getY() < 0 || newPosition.getY() >= height) {
            return false;
        }

        // Verifique se a nova posição colide com uma parede
        for (Wall wall : walls) {
            if (wall.getPosition().equals(newPosition)) {
                return false;
            }
        }

        // Verifique se a nova posição colide com uma moeda
        for (Coin coin : coins) {
            if (coin.getPosition().equals(newPosition)) {
                return false;
            }
        }

        // Verifique se a nova posição colide com o herói
        if (hero.getPosition().equals(newPosition)) {
            return false;
        }

        return true;
    }
    public void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (hero.collidesWithMonster(monster)) {
                if (hero.getEnergy() <= 0) {
                    gameOver = true;
                    break;
                }
            }
        }
    }
    public void draw(TextGraphics textGraphics, Screen screen) throws IOException {
        textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width * 2, height *2),' ');
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.putString(new TerminalPosition(width - 10, 2), "Energy: " + hero.getEnergy());
        for (Wall wall : walls){
            wall.draw(textGraphics, screen);
        }
        for (Monster monster : monsters) {
            monster.draw(textGraphics, screen);
        }
        for(Coin coin : coins){
            coin.draw(textGraphics, screen);
        }

        drawGameOverMessage(textGraphics);
        drawCoinCounter(textGraphics);
        hero.draw(screen.newTextGraphics(), screen);
    }
    public void drawGameOverMessage(TextGraphics textGraphics) {
        if (gameOver) {
            textGraphics.setForegroundColor(TextColor.ANSI.RED);
            textGraphics.putString(new TerminalPosition(width / 2 - 5, height / 2), "Game Over");
        }
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
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int coinX, coinY;
            do {
                coinX = random.nextInt(width - 2) + 1;
                coinY = random.nextInt(height - 2) + 1;
            } while (isCoinOverlap(coinX, coinY) || isCoinOnHero(coinX, coinY));
            coins.add(new Coin(coinX, coinY));
        }
        return coins;
    }
    private boolean isCoinOnHero(int x, int y) {
        return hero.getPosition().getX() == x && hero.getPosition().getY() == y;
    }
    public void retrieveCoins() {
        Position heroPosition = hero.getPosition();
        Iterator<Coin> coinIterator = coins.iterator();
        while (coinIterator.hasNext()) {
            Coin coin = coinIterator.next();
            if (coin.getPosition().equals(heroPosition)) {
                coinIterator.remove();
                incrementCoinCount();
            }
        }
    }
    private boolean isCoinOverlap(int x, int y) {
        for (Coin coin : coins) {
            if (coin.getPosition().getX() == x && coin.getPosition().getY() == y) {
                return true;
            }
        }
        return false;
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

    public void incrementCoinCount() {
        coinCount++;
    }
    public void drawCoinCounter(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.YELLOW);
        textGraphics.putString(new TerminalPosition(width - 10, 1), "Coins: " + coinCount);
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
