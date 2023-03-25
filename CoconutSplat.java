import java.util.*;

public class CoconutSplat {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();

        int[] hand_states = new int[2*n];
        //hand states will be represented by numbers
                // 0: folded, second hand not in game
                // 1: folded
                // 2: fist
                // 3: palm down
                // 4: out of game

        //create handstates of 10101010 first
        for (int i = 0; i < 2*n; i++){
            if (i % 2 == 0){
                hand_states[i] = 1; //default is 0
            }
        }

//        System.out.println(Arrays.toString(hand_states));
//        System.out.println(hand_states[0]);
//        System.out.println(hand_states[1]);

        int existing_players = n;
        int curr = 0;
        while (existing_players > 1){
            int taps = 0;
            //go through taps first
            while (taps < s){
                if (hand_states[curr] == 1){
                    taps++;
                }
                else if (hand_states[curr] == 2){
                    taps++;
                }
                else if (hand_states[curr] == 3){
                    taps++;
                }
                //System.out.println("taps" + Integer.toString(taps) + " curr" + Integer.toString(curr));
                curr = (curr + 1)%(2*n);
            }
            //after reaching the last tap, change hand_states
            int prev = (curr - 1 + 2*n)%(2*n);
            //System.out.println("supposed next tap: " + Integer.toString(curr));
            //System.out.println("last tap: " + Integer.toString(prev));
            if (hand_states[prev] == 1) {
                hand_states[prev] = 2;
                hand_states[curr] = 2; //jump from 0 to 2 because now in game
                curr = prev;
                //System.out.println(Arrays.toString(hand_states));
            }
            else if (hand_states[prev] == 2){
                hand_states[prev] = 3;
                //System.out.println(Arrays.toString(hand_states));
            }
            else {
                hand_states[prev] = 4;
                //System.out.println(Arrays.toString(hand_states));
                //check if player is completely out of game
                if (prev % 2 == 0 && hand_states[curr] == 4){
                    //prev is "first hand" and "second hand" is out of game as well
                    existing_players--;
                }
                else if (prev % 2 != 0) {
                    //means it is at second hand, we need to check the first hand
                    int first_hand = (prev - 1 + 2*n)%(2*n);
                    if (hand_states[first_hand] == 4){
                        //first hand out as well
                        existing_players--;
                    }
                }
                //System.out.println("existing players: " + Integer.toString(existing_players));
            }
        }

        //after only one player is left, find index and print
        for (int j = 0; j < 2*n; j++){
            if (hand_states[j] != 0 && hand_states[j] != 4){
                int ans = (j + 2)/2; //because of automatic round down by java
                System.out.println(ans);
                break;
            }
        }

    }
}