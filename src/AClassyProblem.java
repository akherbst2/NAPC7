import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * Created by Alyssa on 10/22/2016.
 */
public class AClassyProblem {

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        HashMap<String, Integer> comp = new HashMap<>();
        comp.put("upper", -1);
        comp.put("middle", 0);
        comp.put("lower", 1);
        int T = Integer.parseInt(sc.next());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            int N = Integer.parseInt(sc.next());
            HashMap<String, String[]> people = new HashMap<>();
            for(int n = 0; n < N; n++) {
                String[] line = sc.readNextLine().split(" ");
                String[] cls = line[1].split("-");
                people.put(line[0], cls);
                //ignore line[2]
            }
            List<Map.Entry<String, String[]>> peeps = new ArrayList<>(people.entrySet());
            Collections.sort(peeps, new Comparator<Map.Entry<String, String[]>>() {
                @Override
                public int compare(Map.Entry<String, String[]> o1, Map.Entry<String, String[]> o2) {
                    int i1 = o1.getValue().length - 1;
                    String[] val1 = o1.getValue();
                    int i2 = o2.getValue().length - 1;
                    String[] val2 = o2.getValue();
                    //while indices in range and their class values ARE equal
                    while((i1 > 0)&&(i2 > 0)&&comp.get(val1[i1]).equals(comp.get(val2[i2]))) {
                        i1--;
                        i2--;
                    }

                    int comp1 = comp.get(o1.getValue()[i1]);
                    int comp2 = comp.get(o2.getValue()[i2]);
                    if(comp1 != comp2) {
                        return comp1 - comp2;
                    }
                    else {  //if the final values are the same, ie.  middle-lower and lower
                            //keep moving while idx is in bounds and the comp value is 0.
                        while((i1 > 0)&&(comp1 == comp2)) {
                            i1--;
                            comp1 = comp.get(o1.getValue()[i1]);
                            comp2 = 0;
                        }

                        while((i2 > 0)&&(comp1 == comp2)) {
                            i2--;
                            comp2 = comp.get(o2.getValue()[i2]);
                            comp1 = 0;
                        }

                        if(comp1 == comp2) {
                            return o1.getKey().compareTo(o2.getKey());
                        }
                        else {
                            return comp1 - comp2;
                        }
                    }
                }
            });

            for(int i = 0; i < peeps.size(); i++) {
                String name = peeps.get(i).getKey();
                sb.append(name.substring(0, name.length() - 1));
                sb.append("\n");
            }
            for(int i = 0; i < 30; i++) {
                sb.append("=");
            }
            sb.append("\n");

        }
        System.out.print(sb);
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
