package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

import java.util.ArrayList;
import java.util.List;

public class Day6 extends AOCRunner {
    public static void main(String[] args) {
        new Day6().run(2025, 6);
    }

    @Override
    public String part1(String input) {
        var split = input.split("\n");
        List<Calculation> calculations = new ArrayList<>();

        for (int i = 0; i < split[0].split("\\s+").length; i++) {
            Calculation calc = new Calculation();
            for (String string : split) {
                if (string.contains("*") || string.contains("+")) {
                    calc.setOperator(string.trim().split("\\s+")[i]);
                } else {
                    // This parseint trim split is performance culprit. Improvement?
                    calc.addNumber(Integer.parseInt(string.trim().split("\\s+")[i]));
                }
            }
            calculations.add(calc);
        }

        return "" + calculations.stream()
                .mapToLong(Calculation::getSum)
                .sum();
    }

    private static class Calculation {
        private final List<Integer> numbers = new ArrayList<>();
        private String operator;

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public void addNumber(int number) {
            numbers.add(number);
        }

        public Long getSum() {
            return switch (this.getOperator()) {
                case "+" -> numbers.stream()
                        .mapToLong(Integer::longValue)
                        .sum();
                case "*"  -> numbers.stream()
                        .mapToLong(Integer::longValue)
                        .reduce(1, (a, b) -> a * b);
                default -> throw new IllegalStateException("Unexpected value: " + this.getOperator());
            };
        }
    }

    @Override
    public String part2(String input) {
        return "";
    }
}
