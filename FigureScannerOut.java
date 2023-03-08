import java.io.*;
import java.util.*;

public class FigureScannerOut {
    public static void main(String[] args) throws IOException{
        int i = 3;
        int[] line_up = {i,i,i,i};
        ArrayList<String> names = new ArrayList<String>();
        names.add("asdjfhahsd");
        names.add("1");
        names.add("2");
        names.add("3");
        System.out.println(names.get(1));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//        int n = Integer.parseInt(br.readLine());
//
//


//       for (int i = 0; i < n; i++){
//           String temp = br.readLine();
//           String[] temp1 = temp.split(" ");
//           for (int j = 0; j < temp1.length; j ++){
//               pw.println(temp1[j]);
//           }
//
//       }
//       pw.flush();


//        int n = Kattio.getInt();
//        for (int i = 0; i < n; i++){
//            String temp1 = Kattio.getWord();
//            Double temp2 = Kattio.getDouble();
//            Double temp3 = Kattio.getDouble();
//            System.out.println(temp1);
//            System.out.println(temp2);
//            System.out.println(temp3);
//        }


//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//
//        StringBuilder str = new StringBuilder();
//
//        //create hashmap
//        HashMap<String, String> KeyPress = new HashMap<String, String>();
//        Integer ascii_num = 97;
//
//        for (int j = 2; j <= 9; j++){
//            if ((j>= 2 && j <= 6)|| j == 8){
//                KeyPress.put(Character.toString(ascii_num), String.valueOf(j));
//                ascii_num++;
//                for (int k = 2; k <= 3; k++){
//                    KeyPress.put(Character.toString(ascii_num), Integer.toString(j).repeat(k));
//                    ascii_num++;
//                }
//            }
//            else {
//                KeyPress.put(Character.toString(ascii_num), Integer.toString(j));
//                ascii_num++;
//                for (int k = 2; k <= 4; k++){
//                    KeyPress.put(Character.toString(ascii_num), Integer.toString(j).repeat(k));
//                    ascii_num++;
//                }
//            }
//        }
//
//        pw.print(KeyPress);
//        pw.flush();

    }
}

