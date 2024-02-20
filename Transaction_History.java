public class Transaction_History {
    public static void hist(Node k, String fetch) {
        enqueue(k, fetch);
    }

    public static void enqueue(Node k, String fetch) {
        if (k.rear <= k.arr.length) {
            k.arr[k.front++] = fetch;
        } else {
            dequeue(k);
        }
    }

    public static void dequeue(Node k) {
        k.rear++;
    }

    public static void queueprint(Node k) {
        if (k.front == 0) {
            System.out.println("No Transactions Occured from " + k.username);
        } else {
            for (int i = k.rear; i < k.front; i++) {
                System.out.println(k.arr[i]);
            }
        }
    }

}
