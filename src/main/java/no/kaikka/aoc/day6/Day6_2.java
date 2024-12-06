package no.kaikka.aoc.day6;

import no.kaikka.aoc.utils.AOCUtils;

public class Day6_2 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String[] input = AOCUtils.getInput(6).split("\n");

        final char BLOCKADE = '#';
        final char TAPPED_THAT = 'X';
        int count = 0;
        int startingYIndex = -1;
        int startingXIndex = -1;

        for (int i = 0; i < input.length; i++) {
            if (input[i].contains("^")) {
                startingXIndex = input[i].indexOf("^");
                startingYIndex = i;
                break;
            }
        }

        // Very good O(n) :^)
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length(); j++) {
                Direction direction = Direction.UP;
                int yPos = startingYIndex;
                int xPos = startingXIndex;
                String[] inputCopy = new String[input.length];
                System.arraycopy(input, 0, inputCopy, 0, input.length);

                if (inputCopy[i].charAt(j) == BLOCKADE || inputCopy[i].startsWith("^", j)) {
                    continue;
                }

                if (inputCopy[i].startsWith(".", j)) {
                    StringBuilder replacement = new StringBuilder(inputCopy[i]);
                    replacement.setCharAt(j, BLOCKADE);
                    inputCopy[i] = replacement.toString();
                }

                boolean infinite = false;
                while (true) {
                    char currentChar = inputCopy[yPos].charAt(xPos);

                    if (direction == Direction.LEFT) {
                        if (xPos == 0) break;
                        boolean isBlocker = inputCopy[yPos].charAt(xPos-1) == BLOCKADE;
                        if (isBlocker && currentChar == TAPPED_THAT) {
                            // We've been here before and encountered the blockade
                            // Or at least this was the plan. Had a working solution, then tried to refactor into this for slightly better performance,
                            // but now I'm missing something :^)
                            infinite = true;
                            break;
                        }

                        StringBuilder replacement = new StringBuilder(inputCopy[yPos]);
                        replacement.setCharAt(xPos, TAPPED_THAT);
                        inputCopy[yPos] = replacement.toString();

                        if (isBlocker) {
                            direction = direction.next();
                        } else {
                            xPos--;
                        }
                        continue;
                    }

                    if (direction == Direction.DOWN) {
                        if (yPos == inputCopy.length - 1) break;
                        boolean isBlocker = inputCopy[yPos + 1].charAt(xPos) == BLOCKADE;
                        if (isBlocker && currentChar == TAPPED_THAT) {
                            infinite = true;
                            break;
                        }

                        StringBuilder replacement = new StringBuilder(inputCopy[yPos]);
                        replacement.setCharAt(xPos, TAPPED_THAT);
                        inputCopy[yPos] = replacement.toString();

                        if (isBlocker) {
                            direction = direction.next();
                        } else {
                            yPos++;
                        }

                        continue;
                    }

                    if (direction == Direction.RIGHT) {
                        if (xPos + 1 == inputCopy[yPos].length()) break;
                        boolean isBlocker = inputCopy[yPos].charAt(xPos+1) == BLOCKADE;
                        if (isBlocker && currentChar == TAPPED_THAT) {
                            infinite = true;
                            break;
                        }

                        StringBuilder replacement = new StringBuilder(inputCopy[yPos]);
                        replacement.setCharAt(xPos, TAPPED_THAT);
                        inputCopy[yPos] = replacement.toString();
                        if (isBlocker) {
                            direction = direction.next();
                        } else {
                            xPos++;
                        }

                        continue;
                    }

                    if (direction == Direction.UP) {
                        if (yPos == 0) break;
                        boolean isBlocker = inputCopy[yPos - 1].charAt(xPos) == BLOCKADE;
                        if (isBlocker && currentChar == TAPPED_THAT) {
                            infinite = true;
                            break;
                        }

                        StringBuilder replacement = new StringBuilder(inputCopy[yPos]);
                        replacement.setCharAt(xPos, TAPPED_THAT);
                        inputCopy[yPos] = replacement.toString();

                        if (isBlocker) {
                            direction = direction.next();
                        } else {
                            yPos--;
                        }
                        continue;
                    }
                }

                if (infinite) {
                    count++;
                }
            }
        }

        System.out.println(count);
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
