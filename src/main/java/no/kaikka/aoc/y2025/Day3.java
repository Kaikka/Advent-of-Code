package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

import java.util.*;

public class Day3 extends AOCRunner {
    public static void main(String[] args) {
        new Day3().run(2025, 3);
    }


    @Override
    public String part1(String input) {
        int res = 0;
        String[] split = input.split("\n");

        for (String s : split) {
            int max = 0;
            List<String> arr = Arrays.stream(s.split("")).toList();

            for (int i = 0; i < arr.size(); i++) {
                for (int j = i + 1; j < arr.size(); j++) {
                    String str = arr.get(i) + arr.get(j);
                    if (Integer.parseInt(str) > max) {
                        max = Integer.parseInt(str);
                    }
                }
            }
            res += max;
        }

        return "" + res;
    }

    @Override
    public String part2(String input) {
        return "";
    }
}
