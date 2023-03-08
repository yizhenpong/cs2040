import java.io.*;
import java.util.*;

class Mjehuric {
    public static void main(String[]args){
        int[] output = new int[5];
        Kattio io = new Kattio(System.in, System.out);

        for (int i= 0; i < 5; i++){output[i] = io.getInt();}
        Boolean swap = false;

        for (int j= 0; j < 5; j++){
            for (int i= 0; i < 4; i++){
                swap = false;
                if (output[i] > output[i+1]){
                    int temp = output[i];
                    output[i] = output[i+1];
                    output[i+1] = temp;
                    swap = true;
                }
                if (swap){
                    StringBuilder temp = new StringBuilder();
                    temp = new StringBuilder(Integer.toString(output[0]) + " " +
                            Integer.toString(output[1]) + " " +
                            Integer.toString(output[2]) + " " +
                            Integer.toString(output[3]) + " " + Integer.toString(output[4]));
                    io.println(temp);
                }
            }
        }
        io.flush();
    }
}

//testing 123

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
