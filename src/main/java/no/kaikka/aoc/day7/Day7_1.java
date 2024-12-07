package no.kaikka.aoc.day7;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.Arrays;

public class Day7_1 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String[] input = AOCUtils.getInput(7).split("\n");

        long counter = 0;

        for (String s : input) {
            int splitter = s.indexOf(':');
            long goal = Long.parseLong(s.substring(0, splitter));
            int[] numbers = Arrays.stream(s.substring(splitter + 1).trim().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = numbers[0];

            int[] numbersCopy = Arrays.copyOfRange(numbers, 1, numbers.length);

            if (solve(numbersCopy, goal, n)) counter += goal;
        }

        System.out.println("Part 1: " + counter);
        var end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + "ms");
    }

    private static boolean solve(int[] values, long goal, long n) {
        if (n == goal) return true;
        if (values.length == 0 || n > goal) return false;

        long added = n + values[0];
        long multiplied = n * values[0];

        int[] newValues = Arrays.copyOfRange(values, 1, values.length);

        return solve(newValues, goal, added) || solve(newValues, goal, multiplied);
    }
}
