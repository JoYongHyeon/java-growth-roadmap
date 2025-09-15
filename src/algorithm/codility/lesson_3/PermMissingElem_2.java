package algorithm.codility.lesson_3;


public class PermMissingElem_2 {

    public static void main(String[] args) {

        int[] A = {2, 3, 1, 5};
        // 4
        int N = A.length;

        boolean[] used = new boolean[N+2];
        for (int i = 1; i <= N; i++) {
            used[A[i-1]] = true;
        }

        for (int i = 1; i <= used.length; i++) {
            if (!used[i]) {
                System.out.println(i);
                break;
            }
        }
    }
}
