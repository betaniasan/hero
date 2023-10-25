
import Element.*;
import Main.Arena;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

    public class ArenaTest {

        private Arena arena;
        private Screen screen;

        @BeforeEach
        public void setUp() {
            arena = new Arena(20, 20);
            screen = Mockito.mock(Screen.class);
        }

        @Test
        public void testProcessKey() throws IOException {
            KeyStroke keyStroke = Mockito.mock(KeyStroke.class);
            Mockito.when(keyStroke.getKeyType()).thenReturn(KeyType.ArrowUp);

            arena.processKey(keyStroke, screen);

            Position expectedPosition = new Position(10, 9);
            assertEquals(expectedPosition, arena.getHero().getPosition());
        }

        @Test
        public void testAddWall() {
            Wall wall = new Wall(5, 5);
            arena.addWall(wall);

            assertTrue(arena.getWalls().contains(wall));
        }

        @Test
        public void testSetHeroPosition() {
            Position newPosition = new Position(5, 5);
            arena.setHeroPosition(newPosition);

            assertEquals(newPosition, arena.getHero().getPosition());
        }
        @Test
        public void testGameOver() {
            Arena arena = new Arena(20, 20);
            assertFalse(arena.isGameOver());

            arena.gameOver();
            assertTrue(arena.isGameOver());
        }

        @Test
        public void testAddCoin() {
            Arena arena = new Arena(20, 20);
            Coin coin = new Coin(5, 5);

            arena.addCoin(coin);
            assertTrue(arena.getCoins().contains(coin));
        }

        @Test
        public void testAddMonster() {
            Arena arena = new Arena(20, 20);
            Monster monster = new Monster(5, 5, 1);

            arena.addMonster(monster);
            assertTrue(arena.getMonsters().contains(monster));
        }
        @Test
        public void testMoveMonsters() {
            Arena arena = new Arena(20, 20);
            Monster monster = Mockito.mock(Monster.class);
            Mockito.when(monster.move()).thenReturn(new Position(2, 2));

            arena.addMonster(monster);
            arena.moveMonsters();

            assertEquals(new Position(2, 2), monster.getPosition());
        }

        @Test
        public void testCanMonsterMove() {
            Arena arena = new Arena(20, 20);
            Wall wall = new Wall(2, 2);
            Coin coin = new Coin(3, 3);
            Hero hero = new Hero(4, 4, 3);

            arena.addWall(wall);
            arena.addCoin(coin);
            arena.setHeroPosition(hero.getPosition());

            assertFalse(arena.canMonsterMove(new Position(2, 2), arena.getWalls(),arena.getCoins(),hero));
            assertFalse(arena.canMonsterMove(new Position(3, 3),arena.getWalls(),arena.getCoins(),hero));
            assertFalse(arena.canMonsterMove(new Position(4, 4),arena.getWalls(),arena.getCoins(),hero));
            assertTrue(arena.canMonsterMove(new Position(5, 5),arena.getWalls(),arena.getCoins(),hero));
        }

        @Test
        public void testCreateMonsters() {
            Arena arena = new Arena(20, 20);

            assertEquals(6, arena.getMonsters().size());
        }
        @Test
        public void testVerifyMonsterCollisions() {
            Arena arena = new Arena(20, 20);
            Monster monster = new Monster(1, 1, 10);

            arena.setHeroPosition(new Position(1, 1));
            arena.addMonster(monster);

            assertFalse(arena.isGameOver());
            arena.verifyMonsterCollisions();
            assertTrue(arena.isGameOver());
        }
        @Test
        public void testCreateWalls() {
            Arena arena = new Arena(20, 20);

            assertEquals(76, arena.getWalls().size());
        }

        @Test
        public void testCreateCoins() {
            Arena arena = new Arena(20, 20);

            assertEquals(5, arena.getCoins().size());
        }
        @Test
        public void testIsCoinOnHero() {
            Arena arena = new Arena(20, 20);
            Coin coin = new Coin(10, 10);

            assertFalse(arena.isCoinOnHero(coin.getPosition().getX(), coin.getPosition().getY()));
            arena.addCoin(coin);
            assertTrue(arena.isCoinOnHero(coin.getPosition().getX(), coin.getPosition().getY()));
        }

        @Test
        public void testRetrieveCoins() {
            Arena arena = new Arena(20, 20);
            Coin coin = new Coin(10, 10);

            arena.addCoin(coin);
            assertTrue(arena.getCoins().contains(coin));

            arena.setHeroPosition(new Position(10, 10));
            arena.retrieveCoins();
            assertFalse(arena.getCoins().contains(coin));
        }
    }


