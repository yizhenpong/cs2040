import java.io.*;
import java.util.*;

public class CardTrading {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        //get first line of inputs
        Integer N = io.getInt();
        Integer T = io.getInt();
        Integer K = io.getInt();

        //get number of cards per type, second line of input
        long[] Num_cards_per_type = new long[T];
        //System.out.println(Arrays.toString(Num_cards_per_type));
        for (int i = 0; i < N; i++){
            int temp = io.getInt();
            Num_cards_per_type[temp-1]++;
        }

        //loop through the next T lines
        long[] buy_px = new long[T];
        long[] sell_px = new long[T];
        long earnings_from_selling = 0;
        long[] Cost_by_card_type = new long[T];

        for (int j = 0; j < T; j++){
            //get inputs of buy and sell
            long buy = io.getLong();
            long sell = io.getLong();
            buy_px[j] = buy;
            sell_px[j] = sell;

            //calculations
            long num_cards = Num_cards_per_type[j];
            earnings_from_selling += sell_px[j]*num_cards;
            if (num_cards >= 2){
                Cost_by_card_type[j] = sell_px[j]*2;
            }
            else if (num_cards == 1){
                Cost_by_card_type[j] = sell_px[j] + buy_px[j];
            }
            else {
                Cost_by_card_type[j] = 2* buy_px[j];
            }
        }
        Arrays.sort(Cost_by_card_type);
        long Total_cost = 0;
        for (int i = 0; i < K; i++){
            Total_cost = Total_cost + Cost_by_card_type[i];
        }
        System.out.println(earnings_from_selling - Total_cost);
    }
}

//class Kattio extends PrintWriter {
//    public Kattio(InputStream i) {
//        super(new BufferedOutputStream(System.out));
//        r = new BufferedReader(new InputStreamReader(i));
//    }
//    public Kattio(InputStream i, OutputStream o) {
//        super(new BufferedOutputStream(o));
//        r = new BufferedReader(new InputStreamReader(i));
//    }
//
//    public boolean hasMoreTokens() {
//        return peekToken() != null;
//    }
//
//    public int getInt() {
//        return Integer.parseInt(nextToken());
//    }
//
//    public double getDouble() {
//        return Double.parseDouble(nextToken());
//    }
//
//    public long getLong() {
//        return Long.parseLong(nextToken());
//    }
//
//    public String getWord() {
//        return nextToken();
//    }
//
//
//
//    private BufferedReader r;
//    private String line;
//    private StringTokenizer st;
//    private String token;
//
//    private String peekToken() {
//        if (token == null)
//            try {
//                while (st == null || !st.hasMoreTokens()) {
//                    line = r.readLine();
//                    if (line == null) return null;
//                    st = new StringTokenizer(line);
//                }
//                token = st.nextToken();
//            } catch (IOException e) { }
//        return token;
//    }
//
//    private String nextToken() {
//        String ans = peekToken();
//        token = null;
//        return ans;
//    }
//}

