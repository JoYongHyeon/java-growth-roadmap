package algorithm.programmers;


public class Solution {

    public static void main(String[] args) {

        int storage = 5141;
        int usage = 500;
//        int storage = 1000;
//        int usage = 2000;
        int[] change = {10, -10, 10, -10, 10, -10, 10, -10, 10, -10};

        int total_usage = 0;
        for(int i=0; i<change.length; i++){
            usage = usage + (usage * change[i] / 100);
            total_usage += usage;
            if(total_usage > storage){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(1);
    }
}
