//Pong Yi Zhen
//A0238178U

import java.util.*;
public class PeaSoupAndPancakes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int printed = 0;
        for (int i =0; i < n; i++) {
            int k = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            int hasPeaSoup = 0;
            int hasPancake = 0;
            for (int j =0; j < k; j++){
                String item = sc.nextLine();
                if (item.equals("pea soup")) {hasPeaSoup = 1;}
                else if (item.equals("pancakes")) {hasPancake = 1;}
            }
            if (hasPeaSoup == 1 && hasPancake == 1) {
               System.out.println(name);
               printed = 1;
               break;
            }
        }
        if (printed == 0) {System.out.println("Anywhere is fine I guess");}
    }
}



//take in input
//store first integer as n
//for loop that runs n times{}
    // store next int as k
    // store nextline as name
    // initalise pea_soup_bool = False
    // initalise pancakes_bool = False
    // for loop that runs k times
        //store next line as item
        //if item == pea soup,
            //pea_soup_bool = True
        //else if item == pea soup,
            //pancakes_bool = True
    //if pea_soup_bool == True and pancakes_bool == True,
        //return name
//return Anywhere is fine I guess

//must separate the peasoup and pancake boolean!!
