package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

public class Day1_1 extends AOCRunner {

    public static void main(String[] args) {
        new Day1_1().run();
    }

    @Override
    public String part1(String input) {
        var input2 = input.split("\\s");
        int dialPointValue = 50;
        int timesAtZero = 0;

        for (String line : input2) {
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
        return "" + timesAtZero;
    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    private static Direction getDirection(char c) {
        return (c == 'L') ?  Direction.LEFT : Direction.RIGHT;
    }

    @Override
    public String part2(String input) {
        /*
        Seems rather easy, do same as part 1 but also count if it goes <0 or >99 (can happen multiple times)
         */
        return "Not yet implemented";
    }
}
