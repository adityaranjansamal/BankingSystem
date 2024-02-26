/*
 * Handles all the Linked List Creations
 */
public class Node {
    String username;
    long accno;
    String hash_pin;
    double balance;
    String arr[] = new String[100];
    int front, rear;
    byte request;
    String query;
    String upload_file;
    Node next;

    Node() {
        username = "";
        accno = 0L;
        hash_pin = "";
        balance = 0.0d;
        front = 0;
        rear = 0;
        request = 0;
        query = "";
        upload_file = "";
        next = null;
    }
}
