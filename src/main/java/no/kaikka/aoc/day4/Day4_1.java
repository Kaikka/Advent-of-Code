package no.kaikka.aoc.day4;


import no.kaikka.aoc.utils.AOCUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day4_1 {
    public static void main(String[] args) {
        List<String> input = Arrays.stream(AOCUtils.getInput().split("\n")).toList();

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            res.addAll(input);
            res.addAll(diagonalMutation(input));
            input = verticalMutation(input);
        }

        long sum = res.stream()
                .mapToLong(Day4_1::findMatches)
                .sum();

        System.out.println("Del 1 v2: " + sum);
    }

    private static List<String> verticalMutation(List<String> input) {
        List<String> res = new ArrayList<>();
        for (int i = input.size() - 1; i >= 0; i--) {
            StringBuilder str = new StringBuilder();
            for (String s : input) {
                str.append(s.charAt(i));
            }
            res.add(str.toString());
        }
        return res;
    }

    private static List<String> diagonalMutation(List<String> input) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < i+1; j++) {
                str.append(input.get(i - j).charAt(j));
            }
            res.add(str.toString());
        }

        for (int i = input.size() - 1; i > 0; i--) {
            StringBuilder str = new StringBuilder();
            for (int j = input.size()-i, z = input.size() - 1; j < input.get(i).length() && z > 0; j++, z--) {
                str.append(input.get(z).charAt(j));
            }
            res.add(str.toString());
        }
        return res;
    }

    private static long findMatches(String line) {
        return Stream.of(Pattern.compile("XMAS"), Pattern.compile("SAMX"))
                .flatMap(p -> p.matcher(line).results())
                .count();
    }
}
