package Misc;

import Main.*;
import Element.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArenaLoader {
    public static Arena loadArenaFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int width = 0;
        int height = 0;

        // Primeira passagem para determinar a largura e altura da arena
        while ((line = reader.readLine()) != null) {
            width = Math.max(width, line.length());
            height++;
        }

        reader.close();

        Arena arena = new Arena(width, height);

        reader = new BufferedReader(new FileReader(filePath));
        int y = 0;

        // Segunda passagem para criar objetos com base no arquivo
        while ((line = reader.readLine()) != null) {
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);

                if (c == '#') {
                    arena.addWall(new Wall(x, y));
                } else if (c == 'H') {
                    arena.setHeroPosition(new Position(x,y));
                } else if (c == 'C') {
                    arena.addCoin(new Coin(x, y));
                } else if (c == 'M') {
                    arena.addMonster(new Monster(x, y,1));
                }
            }

            y++;
        }

        reader.close();

        return arena;
    }
}
