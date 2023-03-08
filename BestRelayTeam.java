import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class BestRelayTeam {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());
        //getting inputs
        ArrayList<String> Names = new ArrayList<String>();
        ArrayList<Double> FirstLeg = new ArrayList<Double>();
        ArrayList<Double> AnyLeg = new ArrayList<Double>();
        ArrayList<Double> AnyLegArranged = new ArrayList<Double>();

        for (int i = 0; i < n; i++){
            String runner_info = br.readLine();
            String[] info_array = runner_info.split(" ");
            Names.add(info_array[0]);
            FirstLeg.add(Double.parseDouble(info_array[1]));
            AnyLeg.add(Double.parseDouble(info_array[2]));
            AnyLegArranged.add(Double.parseDouble(info_array[2]));
        }
        Collections.sort(AnyLegArranged);

        int[] Best_line_up = new int[4];
        Double Best_timing = 80.0;

        for (int i = 0; i < n; i++){

            //choose first leg
            Double timing = FirstLeg.get(i);
            int[] line_up = {i,i,i,i};
            int numAnyLegs = 1;
            int next_best = 0;

            //choose the top 3 best for any leg
            while (numAnyLegs < 4){
                //search and store index of the next best
                int index = 0;
                while (index < n){
                    //locate next best index
                    //make sure not the same person
                    if (index != line_up[0] && index != line_up[1] && index != line_up[2]
                            && index != line_up[3] && AnyLeg.get(index).equals(AnyLegArranged.get(next_best))){
                        line_up[numAnyLegs] = index;
                        numAnyLegs++;
                        timing += AnyLeg.get(index);
                        break;
                    }
                    else {
                        //continue looping to see if the next person matches anyleg timing
                        index++;
                    }

                }
                //increment if loop through and never find (because next best is i and no other w same timing)
                next_best++;
            }
            if (timing < Best_timing) {
                Best_timing = timing;
                Best_line_up = line_up;
            }

        }

        pw.println(Best_timing);

        for (int i = 0; i < 4; i++){
            pw.println(Names.get(Best_line_up[i]));
        }
        pw.flush();
    }
}


//6
//ASHMEADE 1 4
//BLAKE 2 5
//BOLT 3 2
//CARTER 4 1
//FRATER 5 6
//POWELL 6 2

//answer is Ash, bolt, carter, powell
//but i got ash, bolt carter, bolt. because i forgot to accoutn that i found the person before


//    ArrayList<String> Names = new ArrayList<String>();
//    ArrayList<Double> FirstLeg = new ArrayList<Double>();
//    ArrayList<Double> AnyLeg = new ArrayList<Double>();
//    HashMap<Double, Integer> AnyLegIndex = new HashMap<Double, Integer>();
//
//    //get inputs
//    Scanner sc = new Scanner(System.in);
//    int n = sc.nextInt();
//        sc.nextLine();
//
//                for (int i = 0; i < n; i++){
//        String[] Runner_info = sc.nextLine().split(" ");
//        Names.add(Runner_info[0]);
//        FirstLeg.add(Double.parseDouble(Runner_info[1]));
//        AnyLeg.add(Double.parseDouble(Runner_info[2]));
//        AnyLegIndex.put(AnyLeg.get(i), i);
//        }
//
//        //sort the any leg
//        Collections.sort(AnyLeg);
//
//        //initialise variables to store best
//        Double best_timing = 80.0;
//        ArrayList<Integer> best_line_up = new ArrayList<Integer>();
//
//        //try all combinations, fix first leg first
//        for (int i = 0; i < n; i++){
//        Double timing = 0.0;
//        timing += FirstLeg.get(i);
//        ArrayList<Integer> line_up = new ArrayList<Integer>();
//        line_up.add(i);
//        Integer j = 0;
//        //using the sorted any leg, get the next 4 best "any legs"
//        //make sure no overlap with the first leg
//        while (line_up.size() < 4) {
//        if (AnyLegIndex.get(AnyLeg.get(j))!= i) {
//        timing += AnyLeg.get(j);
//        line_up.add(AnyLegIndex.get(AnyLeg.get(j)));
//        }
//        j += 1;
//        }
//        if (timing < best_timing){
//        best_timing = timing;
//        best_line_up = line_up;
//        }
//        }
//
//        System.out.println(best_timing);
//        for (int k= 0; k < 4; k++){
//        System.out.println(Names.get(best_line_up.get(k)));
//        }