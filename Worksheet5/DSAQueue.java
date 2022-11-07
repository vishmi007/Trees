
/*********************************************************
 * Author: Vishmi Kalansooriya                               
 * Date:  24th August 2021                                    
 * Purpose: An abstract class for the shuffling queue and the circular queue.                 
 *********************************************************************************************************/
import java.util.*;

abstract class DSAQueue {

    public abstract int getCount();

    public abstract boolean isFull();

    public abstract boolean isEmpty();

    public abstract void enQueue(Object pPass) throws Exception;

    public abstract Object deQueue() throws Exception;

    public abstract Object peek() throws Exception;

}
