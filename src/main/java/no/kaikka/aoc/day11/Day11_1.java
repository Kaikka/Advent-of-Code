package no.kaikka.aoc.day11;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11_1 {
    public static void main(String[] args) {
        List<Long> input = Arrays.stream(AOCUtils.getInput(11).trim().split(" ")).map(Long::valueOf).toList();
        var start = System.nanoTime();

        final int BLINKS = 25;

        for (int i = 0; i < BLINKS; i++) {
            List<Long> temp = new ArrayList<>();
            for (Long n : input) {

                if (n == 0) {
                    temp.add(1L);
                } else if (String.valueOf(n).length() % 2 == 0) {
                    String str = String.valueOf(n);
                    temp.add(Long.valueOf(str.substring(0, str.length() / 2)));
                    temp.add(Long.valueOf(str.substring(str.length() / 2)));
                } else {
                    temp.add(n * 2024);
                }
                input = temp;
            }
        }



        System.out.println(input.size());
        var end = System.nanoTime();
        System.out.println("Runtime: " + (end - start)/1000000 + "ms");
    }
}
