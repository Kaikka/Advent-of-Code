package no.kaikka.aoc.y2025;

import no.kaikka.aoc.utils.AOCRunner;

import java.util.ArrayList;
import java.util.List;

public class Day5 extends AOCRunner {
    public static void main(String[] args) {
        new Day5().run(2025, 5);
    }

    @Override
    public String part1(String input) {
        long res = 0L;

        List<Range> ranges = new ArrayList<>();

        for (String line : input.split("\n")) {
            if (line.isEmpty()) continue;
            if (line.contains("-")) {
                Range r = new Range(Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1]));
                ranges.add(r);
            } else {
                if (isInRange(line, ranges)) {
                    res++;
                }
            }
        }
        return "" + res;
    }

    @Override
    public String part2(String input) {
        List<Range> ranges = new ArrayList<>();

        for (String line : input.split("\n")) {
            if (line.isEmpty()) break; // Could also wrap code below in if-check for contains("-")

            Range r = new Range(Long.parseLong(line.split("-")[0]), Long.parseLong(line.split("-")[1]));
            List<Range> rangesToReplace = findOverlappingRanges(r, ranges);

            if (rangesToReplace.isEmpty()) {
                ranges.add(r);
            } else {
                Range nr = r.merge(rangesToReplace);
                ranges.removeAll(rangesToReplace);
                ranges.add(nr);
            }
        }

        return "" + ranges.stream()
                .mapToLong(Range::sum)
                .sum();
    }

    private List<Range> findOverlappingRanges(Range r, List<Range> ranges) {
        List<Range> overlappingRanges = new ArrayList<>();
        for (Range range : ranges) {
            if (range.isOverlapping(r)) {
                overlappingRanges.add(range);
            }
        }
        return overlappingRanges;
    }

    public record Range(Long min, Long max) {
        private boolean isInRange(String n) {
            long num = Long.parseLong(n);
            return num > min && num <= max;
        }

        private boolean isOverlapping(Range r) {
            if (r.min() <= this.max() && r.max() >= this.min()) {
                return true;
            }
            if (r.max() >= this.min() && r.min() <= this.max()) {
                return true;
            }
            return false;
        }

        private Range merge(List<Range> r) {
            assert !r.isEmpty();
            long min = Math.min(this.min(), r.stream().mapToLong(Range::min).min().getAsLong());
            long max = Math.max(this.max(), r.stream().mapToLong(Range::max).max().getAsLong());
            return new Range(min, max);
        }

        private Long sum() {
            return (this.max - this.min()) + 1;
        }
    }

    private boolean isInRange(String ingredient, List<Range> ranges) {
        return ranges.stream()
                .anyMatch(r -> r.isInRange(ingredient));
    }
}
