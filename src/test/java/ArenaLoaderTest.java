import Main.Arena;
import Misc.ArenaLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaLoaderTest {

    @Test
    public void testLoadArenaFromFile() throws IOException {
        Arena arena = ArenaLoader.loadArenaFromFile("maps/map1.txt");

        // Verifique se a arena foi carregada corretamente
        assertNotNull(arena);
        assertEquals(80, arena.getWidth());
        assertEquals(24, arena.getHeight());

        // Verifique se o herói, as paredes, as moedas e os monstros foram carregados corretamente
        assertNotNull(arena.getHero());
        assertFalse(arena.getWalls().isEmpty());
        assertFalse(arena.getCoins().isEmpty());
        assertFalse(arena.getMonsters().isEmpty());
    }

    // Adicione mais testes conforme necessário para verificar se todos os elementos foram carregados corretamente
}
