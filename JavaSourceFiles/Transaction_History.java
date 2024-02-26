public class Transaction_History {
    protected static void hist(Node k, String fetch) {
        enqueue(k, fetch);
    }

    protected static void enqueue(Node k, String fetch) {// only enters element in necessary location thus saving space
        if (k.rear != k.arr.length) {
            k.arr[k.front++] = fetch;
        } else {
            dequeue(k, fetch);
        }
    }

    protected static void dequeue(Node k, String fetch) {// this method is used to again reEnter from the starting
                                                         // location, thus overwriting oldest data.
        if (k.rear == k.arr.length) {
            k.rear = 0;
            dequeue(k, fetch);
        } else {
            k.arr[k.rear++] = fetch;
        }
    }

    protected static void queueprint(Node k) {// prints upto front value
        if (k.front == 0) {
            System.out.println("No Transactions Occured from " + k.username);
        } else {
            for (int i = 0; i < k.front; i++) {
                System.out.println(k.arr[i]);
            }
        }
    }

}
