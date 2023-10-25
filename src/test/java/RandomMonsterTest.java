import Element.Position;
import Element.RandomMonster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomMonsterTest {

    @Test
    public void testMove() {
        RandomMonster randomMonster = new RandomMonster(1, 1, 10);

        Position oldPosition = randomMonster.getPosition();
        Position newPosition = randomMonster.move();

        // Verificar se a posição do monstro mudou
        assertNotEquals(oldPosition, newPosition);

        // Verificar se a nova posição está dentro do intervalo esperado
        assertTrue(newPosition.getX() >= 0 && newPosition.getX() <= 2);
        assertTrue(newPosition.getY() >= 0 && newPosition.getY() <= 2);
    }
}
