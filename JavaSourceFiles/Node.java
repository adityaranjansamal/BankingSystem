/*
 * Handles all the Linked List Creations
 */
public class Node {
    protected String username;
    protected long accno;
    protected String hash_pin;
    protected double balance;
    protected String arr[] = new String[100];
    protected int front, rear;
    protected byte request;
    protected String query;
    protected String upload_file;
    protected int number_of_mpin_attempts;
    protected Node next;

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
        number_of_mpin_attempts = 3;
        next = null;
    }
}
