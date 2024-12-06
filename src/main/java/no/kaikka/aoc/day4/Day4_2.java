package no.kaikka.aoc.day4;

import no.kaikka.aoc.utils.AOCUtils;

public class Day4_2 {
    public static void main(String[] args) {
        String[] input = AOCUtils.getInput(4).split("\n");
        int counter = 0;

        for (int i = 1; i < input.length-1; i++) {
            for (int j = 1; j < input[i].length()-1; j++) {
                char c = input[i].charAt(j);
                if (c == 'A') {
                    String verticalFromTopLeft = ""+input[i-1].charAt(j-1) + c + input[i+1].charAt(j+1);
                    String verticalFromTopRight = ""+input[i-1].charAt(j+1)+ c + input[i+1].charAt(j-1);
                    if (isMas(verticalFromTopLeft) && isMas(verticalFromTopRight)) {
                        counter++;
                    }
                }
            }
        }
        System.out.println("Del 2: " + counter);
    }

    private static boolean isMas(String str) {
        return str.matches("SAM") || str.matches("MAS");
    }
}
