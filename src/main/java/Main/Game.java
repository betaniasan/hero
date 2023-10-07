package Main;

import Misc.ArenaLoader;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Terminal terminal;
    Arena arena;
    Screen screen;
    TerminalSize terminalSize ;
    public Game() throws IOException {
        arena = new Arena(80,24);
        terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary

    }
    private void draw() throws IOException {
        //Arena arenapre = ArenaLoader.loadArenaFromFile("maps/map1.txt");
        screen.clear();
        arena.draw(screen.newTextGraphics(), screen);
        screen.refresh();
    }
    public void run() throws IOException {
        while (arena.isRunning()) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
            processKey(key);
        }
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key ) throws IOException {
        arena.processKey(key, screen);
    }


}