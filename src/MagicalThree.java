/**
 * Created by Alyssa on 11/2/2016.
 */

import java.util.*;
import java.io.*;

public class MagicalThree {
    static StringBuilder sb;
    static boolean[] isPrime;
    static int SIZE = (int) (Math.sqrt(Integer.MAX_VALUE));
    static List<Integer> primes;

    public static void main(String[] args) {
        isPrime = sieve(SIZE);
        FastScanner sc = new FastScanner();
        //Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        loop:
        while(true) {
            int n = sc.nextInt();
            if(n == 0) break;
            else if (n == 3) {
                sb.append(4).append('\n');
            }
            else if (n < 7) {
                sb.append("No such base").append('\n');
            }
            else {
                int base = n - 3;
                List<Integer> div = new ArrayList<>();
                int factor = 1;
                for(Integer prime:primes) {
                    while(base % prime == 0) {
                        base /= prime;
                        div.add(prime);
                        if(!isValid(factor)) {
                            factor *= prime;
                        }
                        if(isValid(factor) && isValid(prime)) {
                            sb.append(Math.min(factor, prime)).append('\n');
                            continue loop;
                        }
                    }
                }
                //if base is leftover, it is also a prime
                if (base != 1) {
                    div.add(base);
                }
                if(div.size() == 0) {
                    sb.append(n - 3).append('\n');
                }
                else if(div.get(0) >= 4) {
                    sb.append(div.get(0)).append('\n');
                }
                else {
                    int i = 0;
                    int maxSingle = Integer.MAX_VALUE;
                    while(i < div.size() &&(div.get(i) < 4)) {
                        i++;
                    }
                    if(i != div.size()) {
                        maxSingle = div.get(i);
                    }
                    int i1 = 0;
                    int i2 = 1;
                    while(div.get(i1)*div.get(i2) < 4) {
                        i1++;
                        i2++;
                    }
                    sb.append(Math.min(maxSingle, div.get(i1)*div.get(i2))).append('\n');
                }
            }
        }
        System.out.println(sb);
    }

    static boolean isValid(int n) {
        return n >= 4;
    }

    public static boolean[] sieve(int N)
    {
        primes = new ArrayList<>();

        boolean[] isPrime = new boolean[N + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= N; i++)
        {
            if (isPrime[i]) {
                primes.add(i);
                for (int multiples = i * i; (multiples <= N) && (multiples > 0); multiples += i) {
                    isPrime[multiples] = false;
                }
            }

        }
        isPrime[2] = isPrime[3] = true;
        isPrime[4] = true;
        return isPrime;
    }

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

    }
}