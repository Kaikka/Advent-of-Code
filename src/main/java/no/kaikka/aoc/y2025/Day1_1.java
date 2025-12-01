package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCUtils;

public class Day1_1 {

    public enum Direction {
        LEFT,
        RIGHT
    }

    public static void main(String[] args) {
        var start = System.nanoTime();
        var input = AOCUtils.getInput().split("\\s");
        int dialPointValue = 50;
        int timesAtZero = 0;

        for (String line : input) {
            Direction dir = getDirection(line.charAt(0));
            int n = Integer.parseInt(line.substring(1));
            n = n % 100; // No point in rotating multiple times

            if (dir == Direction.LEFT) {
                dialPointValue -= n;
            } else if (dir == Direction.RIGHT) {
                dialPointValue += n;
            }

            if (dialPointValue > 99) {
                dialPointValue = dialPointValue - 100;
            }
            if (dialPointValue < 0) {
                dialPointValue = dialPointValue + 100;
            }

            if (dialPointValue == 0) timesAtZero++;
        }

        var end = System.nanoTime();
        System.out.printf("Runtime: %.2f ms%n", (end - start) / 1_000_000.0);
        System.out.println(timesAtZero);

    }

    private static Direction getDirection(char c) {
        return (c == 'L') ?  Direction.LEFT : Direction.RIGHT;
    }
}
