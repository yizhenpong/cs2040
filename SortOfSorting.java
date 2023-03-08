import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
class StringDIY {
    String name;
    int first,second;

    public StringDIY(String name){
        this.name = name;
        this.first = (int)name.charAt(0);
        this.second = (int)name.charAt(1);
    }

    public String toString() {
        return this.name;
    }
}

class SortbyName implements Comparator<StringDIY> {
    public int compare(StringDIY a, StringDIY b) {
        if (a.first != b.first) {
            return a.first - b.first;
        }
        else {
            //this will automatically be stable, becuz return 0
            return a.second - b.second;
        }
    }
}
public class SortOfSorting {
    public static void main(String[] args){
        Kattio io = new Kattio(System.in, System.out);

        int N = io.getInt();
        while (N != 0) {
            ArrayList<StringDIY> names = new ArrayList<StringDIY>();
            //getting inputs & storing into array of names
            for (int i = 0; i < N; i++) {
                names.add(new StringDIY(io.getWord()));
            }
            //sort
            Collections.sort(names, new SortbyName());
            //print names
            for (int i = 0; i < N; i++) {
                System.out.println(names.get(i).toString());
            }
            N = io.getInt();
            if (N != 0) {
                //print blank line between test cases
                System.out.println();
            }
        }
    }
}
