package no.kaikka.aoc.y2024.day8;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.*;

public class Day8_2 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        List<char[]> input = Arrays.stream(AOCUtils.getInput(8).split("\n"))
                .map(String::toCharArray).toList();

        Map<String, List<int[]>> antennas = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            char[] arr = input.get(i);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == '.') continue;
                antennas.computeIfAbsent(String.valueOf(arr[j]), k -> new ArrayList<>()).add(new int[]{j, i});
            }
        }

        Set<String> antinodes = new HashSet<>();
        for (Map.Entry<String, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> values = entry.getValue();
            for (int i = 0; i < values.size()-1; i++) {
                for (int j = i+1; j < values.size(); j++) {
                    saveAntinodeLocation(antinodes, values, i, j, input.get(i).length);
                }
            }
        }

        System.out.println("Part 2: " + antinodes.size());
        var end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + "ms");
    }

    private static void saveAntinodeLocation(Set<String> antinodes, List<int[]> values, int i, int j, int maxLen) {
        int x1 = values.get(i)[0];
        int y1 = values.get(i)[1];
        int x2 = values.get(j)[0];
        int y2 = values.get(j)[1];

        save(antinodes, x1, x1 - x2, y1, y1 - y2, maxLen);
        save(antinodes, x2, x2 - x1, y2, y2 - y1, maxLen);
    }


    private static void save(Set<String> antinodes, int x, int xInc, int y, int yInc, int maxLen) {
        for (; x >= 0 && x < maxLen && y >= 0 && y < maxLen; x += xInc, y += yInc) {
            int[] l = new int[]{x, y};
            antinodes.add(l[0] + "," + l[1]);
        }
    }
}
