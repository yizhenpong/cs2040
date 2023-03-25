import java.io.*;
import java.util.*;

public class JoinStrings {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = io.getInt();
        String[] lst = new String[n];
        HashMap<Integer, TailedLinkedList> wordsOrder = new HashMap<Integer, TailedLinkedList>();

        //getting inputs
        for (int i = 1; i <= n; i++) {
            lst[i-1] = io.getWord();
            TailedLinkedList TLL = new TailedLinkedList();
            TLL.addFront(i);
            wordsOrder.put(i,TLL);
        }

//        System.out.println(Arrays.toString(lst));
//        System.out.println(wordsOrder);

        for (int j = 1; j < n; j++){
            int a = io.getInt();
            int b = io.getInt();
//            System.out.println("a: "+Integer.toString(a) + " and b: "+Integer.toString(b));

            TailedLinkedList b_LL = wordsOrder.get(b);
            //retrieve first node of b
            ListNode b_FirstNode = b_LL.getHead();
            //retrieve whole LL of a
            TailedLinkedList a_LL = wordsOrder.get(a);
            //retrieve last node of a
            ListNode a_lastNode = a_LL.getTail();
//            a_LL.print();
//            b_LL.print();

            //join b to a
            a_lastNode.setNext(b_FirstNode);
            a_LL.tail = b_LL.getTail();
            a_LL.num_nodes = a_LL.num_nodes + b_LL.num_nodes;
//            a_LL.print();

            //remove b from hashmap
            wordsOrder.remove(b);
//            System.out.println(wordsOrder);
        }

        //get last remaining key in hashmap
        int last_key = wordsOrder.keySet().iterator().next();
        TailedLinkedList final_order = wordsOrder.get(last_key);
        ListNode curr_Node = final_order.getNodeAtIndex(0);

        for (int k = 0; k < final_order.num_nodes; k++){
            int index = curr_Node.getItem();
            pw.print(lst[index-1]);
            curr_Node = curr_Node.getNext();
        }

        pw.flush();
    }
}


class ListNode {
    /* attributes */
    public int item;
    public ListNode next;

    /* constructors */
    public ListNode(int val) { this(val, null); }

    public ListNode(int val, ListNode n) {
        item = val;
        next = n;
    }

    /* get the next ListNode */
    public ListNode getNext() { return next; }

    /* get the item of the ListNode */
    public int getItem() { return item; }

    /* set the item of the ListNode */
    public void setItem(int val) { item = val; }

    /* set the next reference */
    public void setNext(ListNode n) { next = n; }
}

// list interface for a list of integers
// Note: 1st item is at index 0, last item at index N-1 (where N is number of items in the list)
interface ListInterface {
    public boolean isEmpty(); // Return true if list is empty; otherwise return false.
    public int     size(); // Return number of items in the list

    //  access items in the list
    public int     indexOf(int item); // return index of item if item is found in the list, otherwise return -1
    public boolean contains(int item); // return true if item is in the list false otherwise
    public int     getItemAtIndex(int index); // get item at index
    public int     getFirst(); // get first item
    public int     getLast(); //get last item

    // add items to the list
    public void    addAtIndex(int index, int item); // add item at position index,
    // shifting all current items from index onwards to the right by 1
    // add item to the back if index == size()
    public void    addFront(int item); // add item to front of list
    public void    addBack(int item); // add item to back of list

    // remove items from the list
    public int     removeAtIndex(int index); // remove item at index and return it
    public int     removeFront(); // remove 1st item and return it
    public int     removeBack(); // remove last item and return it

    // print out the list from index 0 to index N-1
    public void    print();
}

class TailedLinkedList implements ListInterface {
    // attributes
    public ListNode head;
    public ListNode tail;
    public int num_nodes;

    // interface methods

    // Return true if list is empty; otherwise return false.
    public boolean isEmpty() { return (num_nodes == 0); }

    // Return number of items in list
    public int size() { return num_nodes; }

    // return index of item if item is found in the list, otherwise return -1
    public int indexOf(int item) {
        int index = 0;

        for (ListNode cur = head; cur != null; cur = cur.getNext()) {
            if (cur.getItem() == item)
                return index;
            else
                index++;
        }
        return -1;
    }

    // return true if item is in the list false otherwise
    public boolean contains(int item) {
        if (indexOf(item) != -1)
            return true;
        return false;
    }

    // get item at index
    public int getItemAtIndex(int index) {
        int counter = 0;
        int item = 0;

        if (index < 0 || index > size()-1) {
            System.out.println("invalid index");
            System.exit(1);
        }
        if (index == size()-1)
            item = tail.getItem();
        else {
            for (ListNode cur = head; cur != null; cur = cur.getNext(), counter++) {
                if (counter == index) {
                    item = cur.getItem();
                    break;
                }
            }
        }
        return item;
    }

    // Return first item
    public int getFirst() { return getItemAtIndex(0); }

    // Return last item
    public int getLast() { return getItemAtIndex(size()-1); }

    // add item at position index, shifting all current items from index onwards to the right by 1
    // pre: 0 <= index <= size()
    public void  addAtIndex(int index, int item) {
        ListNode cur;
        ListNode newNode = new ListNode(item);

        if (index >= 0 && index <= size()) {
            if (index == 0) // insert in front
                insert(null,newNode);
            else if (index == size()) // insert at the back, don't have to move all the way to the back
                insert(tail,newNode);
            else {
                cur = getNodeAtIndex(index-1); // access node at index-1
                insert(cur,newNode);
            }
        }
        else { // index out of bounds
            System.out.println("invalid index");
            System.exit(1);
        }
    }

    // Add item to front of list
    public void addFront(int item) { addAtIndex(0,item); }

    // Add item to back of list
    public void addBack(int item) { addAtIndex(size(),item); }

    // remove item at index and return it
    // pre: 0 <= index < size()
    public int removeAtIndex(int index) {
        ListNode cur;
        int item = 0;

        // index within bounds and list is not empty
        if (index >= 0 && index < size()) {
            if (index == 0) // remove 1st item
                item = remove(null);
            else {
                cur = getNodeAtIndex(index-1); // access node at index-1
                item = remove(cur);
            }
        }
        else { // index out of bounds
            System.out.println("invalid index or list is empty");
            System.exit(1);
        }
        return item;
    }

    // Remove first node of list
    public int removeFront() { return removeAtIndex(0); }

    // Remove last node of list
    public int removeBack() { return removeAtIndex(size()-1); }

    // Print values of nodes in list.
    public void print() {
        if (head == null)
            System.out.println("Nothing to print...");
        else {
            ListNode cur = head;
            System.out.print("List is: " + cur.getItem());
            for (int i=1; i < num_nodes; i++) {
                cur = cur.getNext();
                System.out.print(", " + cur.getItem());
            }
            System.out.println(".");
        }
    }

    // non-interface helper methods
    public ListNode getHead() { return head; }
    public ListNode getTail() { return tail; }

    /* return the ListNode at index */
    public ListNode getNodeAtIndex(int index) {
        int counter = 0;
        ListNode node = null;

        if (index < 0 || index > size()-1) {
            System.out.println("invalid index");
            System.exit(1);
        }
        if (index == size()-1) // return tail if index == size()-1
            return tail;
        for (ListNode cur = head; cur != null; cur = cur.getNext()) {
            if (counter == index) {
                node = cur;
                break;
            }
            counter++;
        }
        return node;
    }

    // insert newNode after the node referenced by cur
    public void insert(ListNode cur, ListNode n) {
        // insert in front
        if (cur == null) {
            n.setNext(head);
            head = n; // update head
            if (tail == null) // update tail if list originally empty
                tail = head;
        }
        else { // insert anywhere else
            n.setNext(cur.getNext());
            cur.setNext(n);
            if (cur == tail) // update tail if inserted new last item
                tail = tail.getNext();
        }
        num_nodes++;
    }

    // remove the node referenced by cur.next, and return the item in the node
    // if cur == null, remove the first node
    public int remove(ListNode cur) {
        int value;

        if (cur == null) { // remove 1st node
            value = head.getItem();
            head = head.getNext(); // update head
            if (num_nodes == 1) // update tail to null if only item in list is removed
                tail = null;
        }
        else { // remove any other node
            value = cur.getNext().getItem();
            cur.setNext(cur.getNext().getNext());
            if (cur.getNext() == null) // update tail if last item is removed
                tail = cur;
        }
        num_nodes--;

        return value;
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











//public class JoinStrings {
//    public static void main(String[] args) {
//        Kattio io = new Kattio(System.in, System.out);
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//
//        int n = io.getInt();
//        String[] lst = new String[n];
//        HashMap<Integer, TailedLinkedList> wordsOrder = new HashMap<Integer, TailedLinkedList>();
//
//        //getting inputs
//        for (int i = 1; i <= n; i++) {
//            lst[i-1] = io.getWord();
//            TailedLinkedList TLL = new TailedLinkedList();
//            TLL.addFront(i);
//            wordsOrder.put(i,TLL);
//        }
//
//        for (int j = 1; j < n; j++){
//            int a = io.getInt();
//            int b = io.getInt();
//
//            //System.out.println("a: "+Integer.toString(a) + " and b: "+Integer.toString(b));
//
//            TailedLinkedList b_LL = wordsOrder.get(b);
//            //retrieve first node of b & num_nodes in b TLL
//            ListNode b_FirstNode = b_LL.getNodeAtIndex(0);
//            int b_num_nodes = b_LL.num_nodes;
//            //retrieve whole LL of a
//            TailedLinkedList a_LL = wordsOrder.get(a);
//            //retrieve last node  of a
//            ListNode a_lastNode = a_LL.getTail();
//            //join b to a
//            a_LL.insert(a_lastNode, b_FirstNode);
//            a_LL.num_nodes = a_LL.num_nodes + b_num_nodes - 1;
//            a_LL.tail = b_LL.getTail();
//
////            for (int k = 0; k < b_LL.num_nodes; k++){
////                ListNode b_node = b_LL.getNodeAtIndex(k);
////                a_LL.insert(a_lastNode, b_node);
////                a_lastNode = a_LL.getTail();
////            }
//
//            //remove b from hashmap
//            wordsOrder.remove(b);
//        }
//
//        //get last remaining key in hashmap
//        int last_key = wordsOrder.keySet().iterator().next();
//        TailedLinkedList final_order = wordsOrder.get(last_key);
//
//        for (int k = 0; k < final_order.num_nodes; k++){
//            int index = final_order.getItemAtIndex(k);
//            pw.print(lst[index-1]);
//        }
//
//        pw.flush();
//    }
//}


//public class JoinStrings {
//    public static void main(String[] args) {
//        Kattio io = new Kattio(System.in, System.out);
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//
//        int n = io.getInt();
//        String[] lst = new String[n];
//        HashMap<Integer, TailedLinkedList> wordsOrder = new HashMap<Integer, TailedLinkedList>();
//
//        //getting inputs
//        for (int i = 1; i <= n; i++) {
//            lst[i-1] = io.getWord();
//        }
//
//        for (int j = 1; j < n; j++){
//            int a = io.getInt();
//            int b = io.getInt();
//
//            if (wordsOrder.containsKey(a)){
//                if (wordsOrder.containsKey(b)){
//                    TailedLinkedList b_LL = wordsOrder.get(b);
//                    ListNode b_FirstNode = b_LL.getNodeAtIndex(0);
//                    TailedLinkedList a_LL = wordsOrder.get(a);
//                    ListNode a_lastNode = a_LL.getTail();
//
//                    for (int k = 0; k < b_LL.num_nodes; k++){
//                        ListNode b_node = b_LL.getNodeAtIndex(k);
//                        a_LL.insert(a_lastNode, b_node);
//                        a_lastNode = b_node;
//                    }
//                    wordsOrder.remove(b);
//                }
//                else {
//                    TailedLinkedList a_LL = wordsOrder.get(a);
//                    a_LL.addBack(b);
//                }
//            }
//            else {
//                if (wordsOrder.containsKey(b)){
//                    TailedLinkedList b_LL = wordsOrder.get(b);
//                    b_LL.addFront(a);
//                    wordsOrder.put(a,b_LL);
//                    wordsOrder.remove(b);
//                }
//                else {
//                    TailedLinkedList TLL = new TailedLinkedList();
//                    TLL.addFront(b);
//                    TLL.addFront(a);
//                    wordsOrder.put(a, TLL);
//                }
//            }
//
//        }
//
//        if (n == 1){
//            pw.print(lst[0]);
//        }
//        else {
//            //get last remaining key in hashmap
//            int last_key = wordsOrder.keySet().iterator().next();
//            TailedLinkedList final_order = wordsOrder.get(last_key);
//
//            for (int k = 0; k < final_order.num_nodes; k++){
//                int index = final_order.getItemAtIndex(k);
//                pw.print(lst[index-1]);
//            }
//        }
//
//        pw.flush();
//    }
//}