package no.kaikka.aoc.day8;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.*;

public class Day8_1 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        List<char[]> input = Arrays.stream(AOCUtils.getInput(8).split("\n"))
                .map(String::toCharArray).toList();

        Map<String, List<int[]>> antennas = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            char[] arr = input.get(i);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == '.') continue;
                antennas.computeIfAbsent(String.valueOf(arr[j]), k -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        Set<String> antinodes = new HashSet<>();
        for (Map.Entry<String, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> values = entry.getValue();
            for (int i = 0; i < values.size()-1; i++) {
                for (int j = i+1; j < values.size(); j++) {
                    int[] a = new int[]{values.get(i)[0] + (values.get(i)[0] - values.get(j)[0]), values.get(i)[1] + (values.get(i)[1] - values.get(j)[1])};
                    int[] b = new int[]{values.get(j)[0] + (values.get(j)[0]-values.get(i)[0]), values.get(j)[1] + (values.get(j)[1]-values.get(i)[1])};
                    saveAntinodeLocation(antinodes, a, b, input.get(i).length);
                }
            }
        }

        System.out.println("Part 1: " + antinodes.size());
        var end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + "ms");
    }

    private static void saveAntinodeLocation(Set<String> antinodes, int[] a, int[] b, int maxLen) {
        save(antinodes, a, maxLen);
        save(antinodes, b, maxLen);
    }

    private static void save(Set<String> antinodes, int[] l, int maxLen) {
        if (l[0] >= 0 && l[0] < maxLen && l[1] >= 0 && l[1] < maxLen) {
            antinodes.add(l[0] + "," + l[1]);
        }
    }
}
