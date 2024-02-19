/*
 * Handles all the Linked List Creations
 */
public class Node {
    String username;
    long accno;
    String hash_pin;
    double balance;
    Node next;

    Node() {
        username = "";
        accno = 0L;
        hash_pin = "";
        balance = 0.0d;
        next = null;
    }
}
