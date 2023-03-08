import java.io.*;
import java.util.HashMap;

public class T9Spelling {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //create hashmap
        HashMap<String, String> KeyPress = new HashMap<String, String>();
        KeyPress.put(" ","0");
        Integer ascii_num = 97;

        for (int j = 2; j <= 9; j++){
            if ((j>= 2 && j <= 6)|| j == 8){
                KeyPress.put(Character.toString(ascii_num), String.valueOf(j));
                ascii_num++;
                for (int k = 2; k <= 3; k++){
                    KeyPress.put(Character.toString(ascii_num), Integer.toString(j).repeat(k));
                    ascii_num++;
                }
            }
            else {
                KeyPress.put(Character.toString(ascii_num), Integer.toString(j));
                ascii_num++;
                for (int k = 2; k <= 4; k++){
                    KeyPress.put(Character.toString(ascii_num), Integer.toString(j).repeat(k));
                    ascii_num++;
                }
            }
        }

        //run test case
        int n = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= n; test_case++){
            String caseString = br.readLine(); //"hi"
            StringBuilder str = new StringBuilder(); //stores output
            str.append("Case #").append(test_case).append(": ");
            String last = "temp";
            //run through "hi"
            for (int i = 0; i < caseString.length(); i++) {
                //"h" get the corresponding "44"
                String curr_key_press = KeyPress.get(Character.toString(caseString.charAt(i)));
                if (i == 0){
                    //keep track of last
                    last = Character.toString(curr_key_press.charAt(0));
                    str.append(curr_key_press);
                }
                else {
                    //check if need to add space
                    if (Character.toString(curr_key_press.charAt(0)).equals(last)) {
                        str.append(" ").append(curr_key_press);
                    }
                    else {
                        str.append(curr_key_press);
                        last = Character.toString(curr_key_press.charAt(0));
                    }
                }

            }
            pw.println(str);
        }
        pw.flush();

    }
}
