package no.kaikka.aoc.y2024.day12;

import no.kaikka.aoc.utils.AOCUtils;

public class Day12_1 {
    public static void main(String[] args) {
        String[] input = AOCUtils.getInput(12).trim().split("\n");
        var start = System.nanoTime();


        var end = System.nanoTime();
        System.out.printf("Runtime: %.5f ms%n", (end - start) / 1_000_000.0);
    }
}
