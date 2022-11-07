
/*********************************************************
 * Author: Vishmi Kalansooriya                               
 * Date:  24th August 2021                                    
 * Purpose: Implementing the shuffeling Queue  class.                   
 *********************************************************************************************************/
import java.util.*;

class DSAQueueShuffling extends DSAQueue {
    // class fields
    protected int count;
    protected Object[] queueArray;
    protected int maximumCapacity;
    protected int rear;
    protected int front;

    // The parameter cunstructor.
    public DSAQueueShuffling(int pMaximumCapacity) {
        maximumCapacity = pMaximumCapacity;
        queueArray = new Object[maximumCapacity];
        front = 0;
        rear = -1;
        count = 0;
    }

    // Getter
    public int getCount() {
        return count;
    }

    /*********************************************************************
     * Name- isFull Date 24/08/2021 import - NOne Export - (rear == maximumCapacity
     * - 1) purpose - to see whether the Queue is Full.
     *********************************************************************/
    public boolean isFull() {
        return (rear == maximumCapacity - 1);
    }

    /*********************************************************************
     * Name- isEmpty Date 24/08/2021 import - NOne Export - (count == 0) purpose -
     * to see whether the Queue is empty.
     *********************************************************************/
    public boolean isEmpty() {
        return (count == 0);
    }

    /*********************************************************************
     * Name- enQueue Date 24/08/2021 import - pass (Object) Export - None purpose -
     * to insert data into the Queue
     *********************************************************************/
    public void enQueue(Object pass) throws Exception {
        if (isFull()) {
            throw new Exception("Sorry the stack is full");
        } else {
            rear++;
            queueArray[rear] = pass;
            count++;
        }
    }

    /*********************************************************************
     * Name- deQueue Date 24/08/2021 import - pass (Object) Export - topValue
     * purpose - to remove data into the Queue
     *********************************************************************/
    public Object deQueue() throws Exception {
        Object topValue = 0;
        if (isEmpty()) {
            throw new Exception("Sorry the stack is empty");
        } else {
            topValue = queueArray[front];
            front++;
            count--;
        }

        return topValue;
    }

    /*********************************************************************
     * Name- peek Date 24/08/2021 import - pass (Object) Export - topValue purpose -
     * tohave a look at the data in the Queue.
     *********************************************************************/
    public Object peek() throws Exception {
        Object topValue = 0;
        if (isEmpty()) {
            throw new Exception("Sorry the stack is empty"); // Throw an exception if the stack is Empty
        } else {
            topValue = queueArray[front];

        }

        return topValue;

    }

    public char stackTop() {
        char answer = '0';
        answer = (Character) (queueArray[count]);
        return answer;
    }

}
