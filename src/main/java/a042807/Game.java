package a042807;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;

public class Game {
    Terminal terminal;
    Screen screen;
    TerminalSize terminalSize ;
    private  int x = 10;
    private int y = 10;

    private boolean quit = false;



    public Game() throws IOException {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            terminalSize = new TerminalSize(40, 20);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            terminal = terminalFactory.createTerminal();
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);

        screen.refresh();
    }
    public void run() throws IOException {
        while(!quit) {
            draw();
            com.googlecode.lanterna.input.KeyStroke key = screen.readInput();
            processKey(key);
        }
    }


    private void processKey(KeyStroke key) throws IOException {
        switch (key.getKeyType()) {
            case ArrowUp:
                y--;
                break;
            case ArrowRight:
                x++;
                break;
            case ArrowDown:
                y++;
                break;
            case ArrowLeft:
                x--;
                break;
            case Character:
                if (key.getCharacter() == 'q') {
                    screen.close();
                    return;
                }
            case EOF:
                quit = true;
        }
    }
}
