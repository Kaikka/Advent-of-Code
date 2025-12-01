package no.kaikka.aoc.y2024.day13;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.ArrayList;
import java.util.List;

public class Day13_1 {
    public static class Game {
        private int x = 0;
        private int y = 0;
        private final Button a;
        private final Button b;
        private final Goal goal;


        public Game(Button a, Button b, Goal goal) {
            this.a = a;
            this.b = b;
            this.goal = goal;
        }
    }

    public record Button(int xIncrementor, int yIncrementor) {}
    public record Goal(int x, int y) {}

    public static void main(String[] args) {
        String[] input = AOCUtils.getInput(13).trim().split("\n");
        var start = System.nanoTime();

        List<Game> games = new ArrayList<>();

        for (int i = 0; i < input.length; i+=4) {
            // "Button A: X+22, Y+88".substring("Button A: X+22, Y+88".indexOf('X')+2, "Button A: X+22, Y+88".indexOf(','))
            // TODO: regexify :^)
            String ax = input[i].substring(input[i].indexOf('X')+2, input[i].indexOf(','));
            String ay = input[i].substring(input[i].indexOf('Y')+2);
            Button buttonA = new Button(Integer.parseInt(ax), Integer.parseInt(ay));

            String bx = input[i+1].substring(input[i+1].indexOf('X')+2, input[i+1].indexOf(','));
            String by = input[i+1].substring(input[i+1].indexOf('Y')+2);
            Button buttonB = new Button(Integer.parseInt(bx), Integer.parseInt(by));

            String goalA = input[i+2].substring(input[i+2].indexOf('X')+2, input[i+2].indexOf(','));
            String goalB = input[i+2].substring(input[i+2].indexOf('Y')+2);
            Goal goal = new Goal(Integer.parseInt(goalA), Integer.parseInt(goalB));

            Game game = new Game(buttonA, buttonB, goal);
            games.add(game);
        }



        var end = System.nanoTime();
        System.out.printf("Runtime: %.5f ms%n", (end - start) / 1_000_000.0);
    }

}
