package no.kaikka.aoc.utils;

public abstract class AOCRunner {
    public void run(int year, int day) {
        String input = AOCUtils.getInput(year, day);
        var startP1 = System.nanoTime();
        String part1 = part1(input);
        var endP1 = System.nanoTime();
        var startP2 = System.nanoTime();
        String part2 = part2(input);
        var endP2 = System.nanoTime();

        System.out.println("Part 1: " + part1);
        System.out.println("Part 2: " + part2);

        System.out.printf("Part 1 runtime: %.2f ms%n", (endP1 - startP1) / 1_000_000.0);
        System.out.printf("Part 2 runtime: %.2f ms%n", (endP2 -  startP2) / 1_000_000.0);

    }

    public abstract String part1(String input);
    public abstract String part2(String input);
}
