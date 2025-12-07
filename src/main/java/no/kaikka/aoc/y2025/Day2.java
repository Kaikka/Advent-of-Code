package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

public class Day2 extends AOCRunner {
    public static void main(String[] args) {
        new Day2().run(2025, 2);
    }

    @Override
    public String part1(String input) {
        long res = 0;
        for (String line : input.trim().split(",")) {
            long start = Long.parseLong(line.split("-")[0]);
            long end = Long.parseLong(line.split("-")[1]);
            for (long i = start; i <= end; i++) {
                if (isInvalid(i)) {
                    res += i;
                }
            }
        }

        return "" + res;
    }

    // I can probably regexify this...somehow
    // str.matches("^(\\w+)\\1$"), but performance is way worse
    private boolean isInvalid(long n) {
        int v = (int) Math.log10(Math.abs(n)) + 1; // Length of number
        if (v % 2 == 1) return false; // Not needed, but makes it a bit faster

        String str = String.valueOf(n);
        String first = str.substring(0, str.length() / 2);
        String last = str.substring(str.length() / 2);
        return first.equals(last);
    }

    @Override
    public String part2(String input) {
        long res = 0;
        for (String line : input.trim().split(",")) {
            long start = Long.parseLong(line.split("-")[0]);
            long end = Long.parseLong(line.split("-")[1]);
            for (long i = start; i <= end; i++) {
                if (isInvalidP2(i)) {
                    res += i;
                }
            }
        }

        return "" + res;
    }

    // I can probably regexify this...somehow
    private boolean isInvalidP2(long n) {
        int v = (int) Math.log10(Math.abs(n)) + 1; // Length of number

        String str = String.valueOf(n);
        String x = "";
        for (int i = 0; i < v / 2; i++) {
            x += str.charAt(i);
            if (str.replaceAll(x, "").isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
