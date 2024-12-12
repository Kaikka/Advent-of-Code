package no.kaikka.aoc.day11;

import no.kaikka.aoc.utils.AOCUtils;

import java.math.BigInteger;
import java.util.*;

@SuppressWarnings("Duplicates")
public class Day11_2 {
    public static void main(String[] args) {
        // Mental todo: I'm sure learning hashmaps better could make this solution a lot more elegant
        List<Long> input = Arrays.stream(AOCUtils.getInput(11).trim().split(" "))
                .map(Long::parseLong)
                .toList();
        var start = System.nanoTime();

        final int BLINKS = 75;
        HashMap<Long, Long> stones = new HashMap<>();

        for (Long l : input) {
            stones.put(l, stones.getOrDefault(l, 0L) + 1);
        }

        for (int i = 0; i < BLINKS; i++) {
            Map<Long, Long> temp = new HashMap<>(stones);
            for (Long n : temp.keySet()) {
                long count = temp.get(n);
                if (n == 0L) {
                    stones.put(1L, stones.getOrDefault(1L, 0L) + count);
                    stones.put(n, stones.getOrDefault(n, 0L) - count);
                } else if (String.valueOf(n).length() % 2 == 0) {
                    String str = String.valueOf(n);
                    long n1 = Long.parseLong(str.substring(0, str.length() / 2));
                    long n2 = Long.parseLong((str.substring(str.length() / 2)));
                    stones.put(n1, stones.getOrDefault(n1, 0L) + count);
                    stones.put(n2, stones.getOrDefault(n2, 0L) + count);
                    stones.put(n, stones.getOrDefault(n, 0L) - count);
                } else {
                    stones.put(n*2024L, stones.getOrDefault(n*2024L, 0L) + count);
                    stones.put(n, stones.getOrDefault(n, 0L) - count);
                }
            }
        }

        System.out.println(stones.values().stream().mapToLong(Long::longValue).sum());
        var end = System.nanoTime();
        System.out.printf("Runtime: %.0f ms%n", (end - start) / 1_000_000.0);
    }
}
