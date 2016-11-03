import java.util.Scanner;

/**
 * Created by Alyssa on 10/22/2016.
 */
public class ScalingRecipes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(sc.next());

        for(int t = 1; t <= T; t++) {
            int R = Integer.parseInt(sc.next());
            int P = Integer.parseInt(sc.next());
            int D = Integer.parseInt(sc.next());
            sb.append("Recipe # ");
            sb.append(t);
            sb.append("\n");
            String[] name = new String[R];
            double[] weight = new double[R];
            double[] perc = new double[R];

            double scale = (D * 1.0) / (P * 1.0);
            int mainIdx = -1;

            for(int j = 0; j < R; j++) {
                name[j] = sc.next();
                weight[j] = Double.parseDouble(sc.next());
                perc[j] = Double.parseDouble(sc.next());
                if(Math.abs(perc[j] - 100) < 0.005) {
                    mainIdx = j;
                }
            }

            double scaleWeightMain = weight[mainIdx] * scale;
            for(int j = 0; j < R; j++) {
                sb.append(name[j]);
                sb.append(' ');
                sb.append((perc[j] * scaleWeightMain) / 100.0);
                sb.append("\n");
            }

            for(int k = 0; k < 40; k++) {
                sb.append('-');
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }
}
