package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

public class Day1 extends AOCRunner {

    public static void main(String[] args) {
        // Why on earth does part 1 run slower than part 2?
        // Part 1 runtime: 3.28 ms
        // Part 2 runtime: 2.03 ms
        new Day1().run();
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
        var input2 = input.split("\\s");
        int dialPointValue = 50;
        int timesAtZero = 0;

        for (String line : input2) {
            Direction dir = getDirection(line.charAt(0));
            int n = Integer.parseInt(line.substring(1));
            timesAtZero = timesAtZero + (n / 100);
            n = n % 100; // No point in rotating multiple times
            int old = dialPointValue;

            if (dir == Direction.LEFT) {
                dialPointValue -= n;
            } else if (dir == Direction.RIGHT) {
                dialPointValue += n;
            }

            if (dialPointValue > 99) {
                dialPointValue = dialPointValue - 100;
                if (dialPointValue != 0 && old != 0) {
                    timesAtZero++;
                }
            }
            if (dialPointValue < 0) {
                dialPointValue = dialPointValue + 100;
                if (dialPointValue != 0 && old != 0) {
                    timesAtZero++;
                }
            }

            if (dialPointValue == 0) timesAtZero++;
        }
        return "" + timesAtZero;
    }
}
