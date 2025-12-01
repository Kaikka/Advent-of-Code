package no.kaikka.aoc.y2024.day7;

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
            long[] numbers = Arrays.stream(s.substring(splitter + 1).trim().split(" ")).mapToLong(Long::parseLong).toArray();

            if (solve(numbers, 1, goal, numbers[0])) counter += goal;
        }

        System.out.println("Part 1: " + counter);
        var end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + "ms");
    }

    private static boolean solve(long[] values, int index, long goal, long n) {
        if (n == goal && index == values.length) return true;
        if (index >= values.length || n > goal) return false;

        long m = values[index];
        long added = n + m;
        long multiplied = n * m;

        return solve(values, index+1, goal, added) || solve(values, index+1, goal, multiplied);
    }
}
