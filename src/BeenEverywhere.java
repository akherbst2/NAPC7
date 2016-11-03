import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/22/2016.
 */
public class BeenEverywhere {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());

        for(int i = 0; i < t; i++) {
            HashSet<String> h = new HashSet<>();
            int n = Integer.parseInt(sc.next());
            for(int j = 0; j < n; j++) {
                h.add(sc.next());
            }
            System.out.println(h.size());

        }


    }

}
