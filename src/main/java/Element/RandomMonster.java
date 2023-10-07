package Element;

import java.util.Random;

public class RandomMonster extends Monster {
    public RandomMonster(int x, int y, int damage) {
        super(x, y, damage);
    }

    @Override
    public Position move() {
        Random random = new Random();
        int deltaX = random.nextInt(3) - 1; // Random number between -1, 0, 1
        int deltaY = random.nextInt(3) - 1;

        // Calculate the new position based on random deltas
        int newX = getPosition().getX() + deltaX;
        int newY = getPosition().getY() + deltaY;

        return new Position(newX, newY);
    }
}

