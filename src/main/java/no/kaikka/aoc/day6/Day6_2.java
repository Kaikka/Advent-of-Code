package no.kaikka.aoc.day6;

import no.kaikka.aoc.utils.AOCUtils;

public class Day6_2 {
    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        String[] rawInput = AOCUtils.getInput(6).split("\n");
        char[][] input = new char[rawInput.length][];

        for (int i = 0; i < input.length; i++) {
            var charr = rawInput[i].toCharArray();
            input[i] = charr;
        }

        final char BLOCKADE = '#';
        final char TAPPED_THAT = 'X';
        int count = 0;
        int startingYIndex = -1;
        int startingXIndex = -1;

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (input[i][j] == '^') {
                    startingXIndex = j;
                    startingYIndex = i;
                }
            }
        }

        // Very good O(n) :^)
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Direction direction = Direction.UP;
                int yPos = startingYIndex;
                int xPos = startingXIndex;
                char[][] inputCopy = new char[input.length][];
                //System.arraycopy(input, 0, inputCopy, 0, input.length);
                for (int c = 0; c < input.length; c++) {
                    inputCopy[c] = input[c].clone();
                }

                if (inputCopy[i][j] == BLOCKADE || inputCopy[i][j] == '^') {
                    continue;
                }

                if (inputCopy[i][j] == '.') {
                    inputCopy[i][j] = BLOCKADE;
                }

                int movesWithoutUnseenSpot = 0;
                while (true) {
                    char currentChar = inputCopy[yPos][xPos];

                    if (movesWithoutUnseenSpot == 4) {
                        count++;
                        break;
                    }

                    if (direction == Direction.LEFT) {
                        if (xPos == 0) break;
                        boolean isBlocker = inputCopy[yPos][xPos-1] == BLOCKADE;

                        inputCopy[yPos][xPos] = TAPPED_THAT;

                        if (isBlocker) {
                            direction = direction.next();
                            if (currentChar == TAPPED_THAT) {
                                movesWithoutUnseenSpot++;
                            }
                        } else {
                            xPos--;
                            if (currentChar != TAPPED_THAT) {
                                movesWithoutUnseenSpot = 0;
                            }
                        }
                        continue;
                    }

                    if (direction == Direction.DOWN) {
                        if (yPos == inputCopy.length - 1) break;
                        boolean isBlocker = inputCopy[yPos + 1][xPos] == BLOCKADE;

                        inputCopy[yPos][xPos] = TAPPED_THAT;

                        if (isBlocker) {
                            direction = direction.next();
                            if (currentChar == TAPPED_THAT) {
                                movesWithoutUnseenSpot++;
                            }
                        } else {
                            yPos++;
                            if (currentChar != TAPPED_THAT) {
                                movesWithoutUnseenSpot = 0;
                            }
                        }

                        continue;
                    }

                    if (direction == Direction.RIGHT) {
                        if (xPos + 1 == inputCopy[yPos].length) break;
                        boolean isBlocker = inputCopy[yPos][xPos+1] == BLOCKADE;

                        inputCopy[yPos][xPos] = TAPPED_THAT;

                        if (isBlocker) {
                            direction = direction.next();
                            if (currentChar == TAPPED_THAT) {
                                movesWithoutUnseenSpot++;
                            }
                        } else {
                            xPos++;
                            if (currentChar != TAPPED_THAT) {
                                movesWithoutUnseenSpot = 0;
                            }
                        }

                        continue;
                    }

                    if (direction == Direction.UP) {
                        if (yPos == 0) break;
                        boolean isBlocker = inputCopy[yPos - 1][xPos] == BLOCKADE;

                        inputCopy[yPos][xPos] = TAPPED_THAT;

                        if (isBlocker) {
                            direction = direction.next();
                            if (currentChar == TAPPED_THAT) {
                                movesWithoutUnseenSpot++;
                            }
                        } else {
                            yPos--;
                            if (currentChar != TAPPED_THAT) {
                                movesWithoutUnseenSpot = 0;
                            }
                        }
                        continue;
                    }
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
