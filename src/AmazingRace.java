/**
 * Created by Alyssa on 10/24/2016.
 */

import java.util.*;
import java.io.*;

/**
 * Created by Alyssa Herbst
 * Attempted solution to a Knapsack Problem.
 * https://open.kattis.com/problems/race
 */
public class AmazingRace {
    static int n, T;
    static int[][] dist;
    static int[][] iptd;
    static int startIdx;
    static int endIdx;
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        T = sc.nextInt();

        startIdx = n;
        endIdx = n + 1;
        dist = new int[n + 2][n + 2];
        Task[] task = new Task[n + 2];

        for(int i = 0; i < n; i++) {
            int p = sc.nextInt();
            int t = sc.nextInt();
            int d = sc.nextInt();
            task[i] = new Task(p, t, d);
        }
        task[n] = new Task(0, 0, T);
        task[n + 1] = new Task(0, 0, T);

        for(int i = 0; i < n + 2; i++) {
            for(int j = 0; j < n + 2; j++) {
                dist[i][j] = sc.nextInt();
            }
        }




    }

    static class Task {
        int p, t, d;

        public Task(int p, int t, int d) {
            this.p = p;
            this.t = t;
            this.d = d;
        }
    }

    static int knapsack(List<Integer> bag) {
        //best value given the capacity of items inside, and the index of the next item
        int[][] bestValue = new int[T+1][iptd.length + 1];

        //Build your knapsack from smallest time to biggest time.
        for (int t = 0; t <= T; t++) {
            //Build knapsack by choosing tasks 1 by 1 to include, to get the most points in time t.
            for (int i = 0; i < iptd.length; i++) {
                //if the next item is less than my max time
                if (iptd[i][1] <= t) {
                    //I set my max of the next item to be the max of
                    //  1. excluding task i
                    //  2. including task i

                    //bestValue[t - iptd[i][1] [i] is the best value of the max of excluding task i from capacity,
                    bestValue[t][i+1] = Math.max(bestValue[t][i], bestValue[t - (iptd[i][1] /* + Time to get there*/)][i] + iptd[i][0]);
                } else {
                    bestValue[t][i+1] = bestValue[t][i];
                }
            }
        }

        /* Choose those items that were include because they improved the best[][] value */
        int maxFill = bestValue[T][iptd.length];
        int remainingFill = maxFill;
        for (int itemIdx = iptd.length; remainingFill >= 0 && itemIdx > 0; itemIdx--) {
            if (bestValue[remainingFill][itemIdx] > bestValue[remainingFill][itemIdx-1]) {
                //bag.add(items[itemIdx-1]);
                //remainingFill -= items[itemIdx-1].weight;
            }
        }
        return maxFill;

        //return the best value of anything less than T, including all items in our array.
        //return bestValue[T][iptd.length];
    }

//    public static int Points(int idx, int time) {
//
//    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(Reader in) {
            br = new BufferedReader(in);
        }

        public FastScanner() {
            this(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String readNextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int[] readIntArray(int n) {
            int[] a = new int[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextInt();
            }
            return a;
        }

        long[] readLongArray(int n) {
            long[] a = new long[n];
            for (int idx = 0; idx < n; idx++) {
                a[idx] = nextLong();
            }
            return a;
        }
    }
}