import java.util.*;
import java.io.*;

public class Conformity {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int frosh = io.getInt();
        HashMap<Integer,Integer> AllCombis = new HashMap<Integer,Integer>();
        //int MaxCombis = 0;

        for (int i = 1; i <= frosh; i++){
            int[] tempArray = new int[5];
            for (int j = 0; j < 5;j++){
                int tempInt = io.getInt();
                tempArray[j] = tempInt;
            }
            Arrays.sort(tempArray);
            int summation = tempArray[0];
            for (int j = 1; j < 5;j++){
                summation = summation*1000 + tempArray[j];
            }
            if (AllCombis.containsKey(summation)){
                AllCombis.put(summation, AllCombis.get(summation)+1);
            }
            else {
                AllCombis.put(summation, 1);
            }
        }


        int MostPopz = Collections.max(AllCombis.values());
        int NumSameMostPopz = 0;

        for (Integer population: AllCombis.values()){
            if (population == MostPopz){
                NumSameMostPopz++;
            }
        }

        io.println(NumSameMostPopz*MostPopz);

        io.close();
    }
}


class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
