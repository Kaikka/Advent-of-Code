package no.kaikka.aoc.y2024.day6;

import no.kaikka.aoc.utils.AOCUtils;

public class Day6_1 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String[] input = AOCUtils.getInput(6).split("\n");

        final String BLOCKADE = "#";
        final char TAPPED_THAT = 'X';
        int y = -1;
        int x = -1;
        Direction direction = Direction.UP;
        int counter = 0;

        for (int i = 0; i < input.length; i++) {
            if (input[i].contains("^")) {
                x = input[i].indexOf("^");
                y = i;
                break;
            }
        }

        while (true) {
            if (direction == Direction.LEFT) {
                if (x == 0) break;
                if (input[y].startsWith(""+TAPPED_THAT, x-1)) {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    x--;
                    continue;
                }
                boolean isBlocker = input[y].startsWith(BLOCKADE, x-1);
                if (isBlocker) {
                    direction = direction.next();
                } else {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    x--;
                    counter++;
                }
                continue;
            }

            if (direction == Direction.DOWN) {
                if (y == input.length-1) break;
                if (input[y+1].startsWith(""+TAPPED_THAT, x)) {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    y++;
                    continue;
                }
                boolean isBlocker = input[y+1].startsWith(BLOCKADE, x);
                if (isBlocker) {
                    direction = direction.next();
                } else {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    y++;
                    counter++;
                }
                continue;
            }

            if (direction == Direction.RIGHT) {
                if (x+1 == input[y].length()) break;
                if (input[y].startsWith(""+TAPPED_THAT, x)) {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    x++;
                    continue;
                }
                boolean isBlocker = input[y].startsWith(BLOCKADE, x+1);
                if (isBlocker) {
                    direction = direction.next();
                } else {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    x++;
                    counter++;
                }
                continue;
            }

            if (direction == Direction.UP) {
                if (y == 0) break;
                if (input[y-1].startsWith(""+TAPPED_THAT, x)) {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    y--;
                    continue;
                }
                boolean isBlocker = input[y-1].startsWith(BLOCKADE, x);
                if (isBlocker) {
                    direction = direction.next();
                } else {
                    StringBuilder replacement = new StringBuilder(input[y]);
                    replacement.setCharAt(x, TAPPED_THAT);
                    input[y] = replacement.toString();
                    y--;
                    counter++;
                }
                continue;
            }
        }
        counter++;


        System.out.println(counter);
        var end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + "ms");
    }


    private enum Direction {
        UP, RIGHT, DOWN, LEFT;

        private Direction next;
        static {
            UP.next = RIGHT;
            RIGHT.next = DOWN;
            DOWN.next = LEFT;
            LEFT.next = UP;
        }

        public Direction next() {
            return next;
        }
    }
}
