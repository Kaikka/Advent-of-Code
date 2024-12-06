package no.kaikka.aoc.day5;

import no.kaikka.aoc.utils.AOCUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day5_1 {
    public static void main(String[] args) {
        String[] input = AOCUtils.getInput(5).split("\n\n");
        String[] rules = input[0].split("\n");
        String[] updates = input[1].split("\n");
        HashMap<String, List<String>> rulesMap = new HashMap<>();
        createRulesMap(rules, rulesMap);

        int res = 0;
        for (String s : updates) {
            boolean legal = true;
            String[] update = s.split(",");
            for (int i = update.length - 1; i > 0; i--) {
                String k = update[i];
                List<String> rightOfKey = rulesMap.get(k);
                if (rightOfKey == null) continue;
                for (String l : rightOfKey) {
                    if (s.substring(0, s.indexOf(k)-1).contains(l)) {
                        legal = false;
                        break;
                    }
                }
                if (!legal) {
                    break;
                }
            }
           if (legal) {
               String[] split = s.split(",");
               int len = split.length;
               res += Integer.parseInt(split[len/2]);
            }

        }
        System.out.println(res);

    }

    private static void createRulesMap(String[] rules, HashMap<String, List<String>> rulesMap) {
        for (String s : rules) {
            String[] ss = s.split("\\|");
            String k = ss[0];
            String v = ss[1];
            rulesMap.computeIfAbsent(k, k1 -> new ArrayList<>()).add(v);
        }
    }
}
