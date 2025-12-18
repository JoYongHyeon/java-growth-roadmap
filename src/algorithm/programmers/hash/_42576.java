package algorithm.programmers.hash;

import network.io_2._1_Reader_Writer_Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class _42576 {

    public static void main(String[] args) {

        // 1. 마라톤에 참여한 사람
        String[] participant = {"leo", "kiki", "eden"};
        // 2. 완주한 사람
        String[] completion = {"eden", "kiki"};

        // 3. 완주 하지 못한 선수는?
        Map<String, Integer> map = new HashMap<>();

        for (String s : participant) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        for (String s : completion) {
            map.put(s, map.getOrDefault(s, 0) - 1);
        }


//        Map<String, Long> collect = Arrays.stream(participant)
//                .collect(Collectors.groupingBy(
//                        name -> name,
//                        Collectors.counting()
//                ));
//
//        for (String s : completion) {
//            collect.put(s, collect.get(s) - 1);
//        }
//
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey());
                break;
            }
        }

    }
}
