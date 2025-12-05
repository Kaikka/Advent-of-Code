package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

import java.util.ArrayList;
import java.util.List;

public class Day4 extends AOCRunner {
    public static void main(String[] args) {
        new Day4().run(2025, 4);
    }

    @Override
    public String part1(String input) {
        int res = 0;
        var arr = input.split("\n");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                char c = arr[i].charAt(j);
                if (c == '.') continue;

                List<Character> adjacent = new ArrayList<>();
                int minX = Math.max(j-1, 0);
                int maxX = Math.min(j+1, arr.length-1);
                int minY = Math.max(i-1, 0);
                int maxY = Math.min(i+1, arr.length-1);

                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y <= maxY; y++) {
                        if (x == j && y == i) continue;
                        var adj = arr[y].charAt(x);
                        if (adj == '@') {
                            adjacent.add(adj);
                        }
                    }
                }
                if (adjacent.size() < 4) {
                    res++;
                }
            }
        }

        return "" +res;
    }

    @Override
    public String part2(String input) {
        int res = 0;
        var arr = input.split("\n");
        boolean running = true;

        while (running) {
            int tempRes = res;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length(); j++) {
                    char c = arr[i].charAt(j);
                    if (c == '.') continue;

                    List<Character> adjacent = new ArrayList<>();
                    int minX = Math.max(j-1, 0);
                    int maxX = Math.min(j+1, arr.length-1);
                    int minY = Math.max(i-1, 0);
                    int maxY = Math.min(i+1, arr.length-1);

                    for (int x = minX; x <= maxX; x++) {
                        for (int y = minY; y <= maxY; y++) {
                            if (x == j && y == i) continue;
                            var adj = arr[y].charAt(x);
                            if (adj == '@') {
                                adjacent.add(adj);
                            }
                        }
                    }
                    if (adjacent.size() < 4) {
                        res++;
                        String newX = arr[i].substring(0, j) + '.' + arr[i].substring(j +1);
                        arr[i] = newX;
                    }
                }
            }
            if (tempRes == res) {
                running = false;
            }
        }

        return "" +res;
    }
}
