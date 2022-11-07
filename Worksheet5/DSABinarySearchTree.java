
import java.io.Serializable;
import java.util.*;
import java.io.*;

public class DSABinarySearchTree implements Serializable {
    /*********************************************************
     * Author: Vishmi Kalansooriya Date: 10th september 2021 Purpose: Implementing
     * the private inner Tree Node class..
     *********************************************************************************************************/
    private class TreeNode implements Serializable {
        private String m_key;
        private Object m_value;
        private TreeNode m_leftChild;
        private TreeNode m_rightChild;

        public TreeNode(String inKey, Object inVal) throws IllegalAccessException {
            if (inKey == null) {
                throw new IllegalAccessException("Sorry ! the key cannot be null "); // Throws an exception if the

            } else {
                m_key = inKey;
                m_value = inVal;
                m_rightChild = null;
                m_leftChild = null;

            }

        }
        // getters

        public String getKey() {
            return m_key;
        }

        public Object getValue() {
            return m_value;
        }

        public TreeNode getLeft() {
            return m_leftChild;
        }

        public TreeNode getRight() {
            return m_rightChild;
        }

        // Setters
        public void setLeft(TreeNode newLeft) {
            m_leftChild = newLeft;
        }

        public void setRight(TreeNode newRight) {
            m_rightChild = newRight;
        }

    }

    private TreeNode m_root;

    public DSABinarySearchTree() {
        m_root = null; // The initial state of the root is null because we start with an empty tree
    }

    /***************************************************************************
     * Name : insert Import : key (String) , data (Object), Export : None Purpose :
     * To wrap the insertRec method.
     **************************************************************************/

    public void insert(String key, Object data) throws IllegalAccessException {
        m_root = insertRec(key, data, m_root);
    }

    /***************************************************************************
     * Name : insertRec Import : key (String) , data (Object), currNode (TreeNode)
     * Export : updateNode (TreeNode) Purpose : To insert data into the Binary
     * Search tree.
     **************************************************************************/

    private TreeNode insertRec(String key, Object data, TreeNode currNode) throws IllegalAccessException {
        TreeNode updateNode = currNode;
        if (currNode == null) // The base case
        {
            TreeNode newNode = new TreeNode(key, data);
            updateNode = newNode;
        } else if (key.equals(currNode.getKey())) // Base case number 2
        {
            throw new IllegalAccessException(" Sorry ! the key exhists");
        } else if (key.compareTo(currNode.getKey()) < 0) // Compares the two strings lexicograpycally
        {
            currNode.setLeft(insertRec(key, data, currNode.getLeft())); // Recurse to the left
        } else {
            currNode.setRight(insertRec(key, data, currNode.getRight())); // Recurse to the right.
        }

        return updateNode;
    }

    /***************************************************************************
     * Name : find Import : key (String) Export : findRec (method value) Purpose :
     * To wrap the find method.
     **************************************************************************/

    public Object find(String key) {
        return findRec(key, m_root);
    }

    /***************************************************************************
     * Name : findRec Import : key (String), currNode (TreeNode) Export : value
     * (Object) Purpose : To Find data in the Binary Search tree.
     ***************************************************************************/

    private Object findRec(String key, TreeNode currNode) {
        Object value = null;

        if (currNode == null) {
            throw new NoSuchElementException(" Key " + key + " cannot be found "); // base case

        } else if (key.equals(currNode.getKey())) // base case 2 // if the key matches the current node value is the
                                                  // currentNode value
        {
            value = currNode.getValue();

        } else if (key.compareTo(currNode.getKey()) < 0) {
            value = findRec(key, currNode.getLeft()); // recurse left until found
        } else {
            value = findRec(key, currNode.getRight()); // recurse right until found
        }

        return value;

    }

    /***************************************************************************
     * Name : delete Import : key (String) Export : deleteRec (method value) Purpose
     * : To wrap the delete method.
     **************************************************************************/

    public void delete(String key) {
        deleteRec(key, m_root);
    }

    /***************************************************************************
     * Name : deleterec Import : key (String), curNode (TreeNode) Export :
     * updateNode(TreeNode) Purpose : To delete data in the Binary Search tree.
     ***************************************************************************/
    private TreeNode deleteRec(String key, TreeNode curNode) {
        TreeNode updateNode = curNode;
        if (curNode == null) // check if the entered key exists in the tree.
        {
            throw new IllegalArgumentException(" Sorry ! the tree is not found");

        } else if (key.equals(curNode.getKey())) // base case 2- node found
        {
            updateNode = deleteNode(key, curNode);

        } else if (key.compareTo(curNode.getKey()) < 0) // Recurse left until found
        {
            curNode.setLeft(deleteRec(key, curNode.getLeft()));
        } else {
            curNode.setRight(deleteRec(key, curNode.getRight())); // Recurse right until found.
        }

        return updateNode;
    }

    /***************************************************************************
     * Name : deleteNode Import : key (String), delNode (TreeNode) Export :
     * updateNode(Object) Purpose : To Find data in the Binary Search tree.
     ***************************************************************************/

    private TreeNode deleteNode(String key, TreeNode delNode) {
        TreeNode updateNode = null;

        if ((delNode.getLeft() == null) && (delNode.getRight() == null)) {
            updateNode = null;
        } else if ((delNode.getLeft() != null) && (delNode.getLeft() == null)) {
            updateNode = delNode.getLeft(); // One child - left
        } else if ((delNode.getLeft() == null) && (delNode.getRight() != null)) {
            updateNode = delNode.getRight(); // one child - right
        } else {
            updateNode = promoteSuccessor(delNode.getRight()); // two children
            updateNode.setRight(delNode.getRight());

        }
        updateNode.setLeft(delNode.getLeft());

        return updateNode;

    }

    /***************************************************************************
     * Name : promoteSuccessor Import : curNode (TreeNode) Export :
     * updateNode(Object) Purpose : To Find data in the Binary Search tree.
     ***************************************************************************/

    private TreeNode promoteSuccessor(TreeNode curNode) {
        TreeNode successor = curNode;

        if (curNode.getLeft() == null) // no left children - base case
        {
            successor = curNode;
        }
        if (curNode.getLeft() != null) {
            successor = promoteSuccessor(curNode.getLeft());

        }

        if (successor == curNode.getLeft()) // Parent of the successor
        {
            curNode.setLeft(successor.getRight()); // needs to adopt the right child
        }

        return successor;
    }

    /*************************************************************
     * Name : inOrder Import : None Export : None Purpose : to wrap the
     * traverseInOrder method.
     **************************************************************/
    public void inOrder() throws Exception {

        traverseInOrder(m_root);

    }

    /***************************************************************************
     * Name : traverseInOrder Import : curNode (TreeNode) Export : None Purpose : To
     * traverse data inorder.
     ******************************************************************************/

    private void traverseInOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node == null) {
            return;
        }

        traverseInOrder(node.getLeft()); // First recur on the left side.
        queue.enQueue(node.getKey()); // Store the data inside a queue inOrder
        while (!queue.isEmpty()) { // Print the data in the queue.
            System.out.println(queue.deQueue());
        }
        traverseInOrder(node.getRight()); // recur on the right side.
    }

    /*************************************************************
     * Name : preOrder Import : None Export : None Purpose : to wrap the
     * traversePreOrder method.
     **************************************************************/
    public void preOrder() throws Exception {

        traversePreOrder(m_root);

    }

    /***************************************************************************
     * Name : traversePreOrder Import : curNode (TreeNode) Export : None Purpose :
     * To traverse datain Preorder.
     ******************************************************************************/

    private void traversePreOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node == null) {
            return;
        }

        queue.enQueue(node.getKey()); // Store data in the queue in Preorder
        while (!queue.isEmpty()) { // Print the stored data in the queue so that they print in preorder becausw its
                                   // FIFO
            System.out.println(queue.deQueue());
        }
        traversePreOrder(node.getLeft()); // First recur on the left side.
        traversePreOrder(node.getRight()); // recur on the right side.
    }

    /*************************************************************
     * Name : postOrder Import : None Export : None Purpose : to wrap the
     * traversePostOrder method.
     **************************************************************/
    public void postOrder() throws Exception {

        traversePostOrder(m_root);

    }

    /***************************************************************************
     * Name : traversePostOrder Import : curNode (TreeNode) Export : None Purpose :
     * To traverse data in PostOrder.
     ******************************************************************************/

    private void traversePostOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node == null) {
            return;
        }

        traversePostOrder(node.getLeft()); // First recur on the left side.
        traversePostOrder(node.getRight()); // recur on the right side.
        queue.enQueue(node.getKey()); // Store data in the queue in Preorder
        while (!queue.isEmpty()) { // Print the stored data in the queue so that they print in preorder becausw its
                                   // FIFO
            System.out.println(queue.deQueue());
        }
    }

    /***************************************************************************
     * Name : minNode Import : None Export : None Purpose : To wrap the minRec
     * method.
     ******************************************************************************/
    public int minNode() {
        return minRec(m_root);
    }

    /***************************************************************************
     * Name : minRec Import : curNode (TreeNode) Export : None Purpose : To find the
     * minimum key ( not value) in the BST
     ******************************************************************************/
    private int minRec(TreeNode curNode) {
        int minKey;
        if (curNode.getLeft() != null) // This is not the base case just checking whether the left side of the root
                                       // node is null.
        {
            minKey = minRec(curNode.getLeft()); // The recursive call
        } else {
            int castKey = Integer.parseInt(curNode.getKey()); // Casting the String into an integer.
            minKey = castKey; // if the left side is null, the minimum key is the root key.
        }
        return minKey;

    }

    /***************************************************************************
     * Name : maxNode Import : None Export : None Purpose : To wrap the maxRec
     * method.
     ******************************************************************************/
    public int maxNode() {
        return maxRec(m_root);
    }

    /***************************************************************************
     * Name : maxRec Import : curNode (TreeNode) Export : None Purpose : To find the
     * maximum key ( not value) in the BST
     ******************************************************************************/
    private int maxRec(TreeNode curNode) {
        int maxKey;
        if (curNode.getRight() != null) // This is not the base case just checking whether the right side of the root
                                        // node is null.
        {
            maxKey = maxRec(curNode.getRight()); // The recursive call
        } else {
            int castKey = Integer.parseInt(curNode.getKey()); // casting the String into an integer .
            maxKey = castKey; // if the right side is null, the maximum key is the root key.
        }
        return maxKey;

    }

    /***************************************************************************
     * Name : height Import : None Export : None Purpose : To wrap the heightRec
     * method.
     ******************************************************************************/
    public int height() {
        return heightRec(m_root);
    }

    /***************************************************************************
     * Name : heightRec Import : curNode (TreeNode) Export : heightSoFar (int)
     * Purpose : To find the height of the BST
     ******************************************************************************/

    public int heightRec(TreeNode curNode) {
        int heightSoFar;
        int iLeftHeight;
        int iRightHeight;

        if (curNode == null) {
            heightSoFar = -1; // Base case // there is no height if the rooth is null.

        } else {
            iLeftHeight = heightRec(curNode.getLeft()); // Calculate the height to the left from the initial point
            iRightHeight = heightRec(curNode.getRight()); // Calculate the height to the right from the initial point.

            // Compare the heights .
            if (iLeftHeight > iRightHeight) {
                heightSoFar = iLeftHeight + 1;
            } else {
                heightSoFar = iRightHeight + 1;
            }

        }

        return heightSoFar;
    }

    /***************************************************************************
     * Name : isBalanced Import : None Export : None Purpose : To wrap the
     * balancedRec method.
     ******************************************************************************/
    public boolean isBalanced() {
        boolean balan = balancedRec(m_root);
        return balan;
    }

    /***************************************************************************
     * Name : balancedRec Import : curNode (TreeNode) Export : check (boolean)
     * Purpose : To check whether the binary search tree is balanced.
     ******************************************************************************/
    private boolean balancedRec(TreeNode curNode) {

        boolean flag = false;
        int leftHeight = 0;
        int rightHeight = 0;

        if (curNode == null) {
            flag = true;
        }

        leftHeight = heightRec(curNode.getLeft());
        rightHeight = heightRec(curNode.getRight());

        if ((Math.abs(leftHeight - rightHeight) <= 1) && (balancedRec(curNode.getLeft()))
                && balancedRec(curNode.getRight()))
        // Using the math class and its Maths.abs method to get the absolute value of
        // the difference
        // here we get the absolute value because we leftHeight can be less than
        // rightHeight but what we want is just the difference.

        // we say that the difference of the right sub and left sub tree should be not
        // more than one. For the tree to be balanced.
        {

            flag = true;

        }

        flag = false;

        return flag;

    }

    /***************************************************************************
     * Name : balanced Import : None Export : None Purpose : To wrap the method
     * percentage.
     ******************************************************************************/
    public double balanced() {
        return percentage(m_root);
    }

    /***************************************************************************
     * Name : percentage Import : curNode (TreeNode) Export : check (boolean)
     * Purpose : To calculate a percentage scoree for how balanced the tree is.
     ******************************************************************************/
    private double percentage(TreeNode curNode) {
        int leftheight = 0, rightheight = 0;
        double percentage = 0.0;
        if (curNode == null) {
            // nothings along this branch
        } else {
            leftheight = heightRec(curNode.getLeft()); // Calc left height from here
            rightheight = heightRec(curNode.getRight()); // Calc right height from here
        }
        percentage = (Double.valueOf(Math.max(leftheight + 1, rightheight + 1))
                - Double.valueOf(Math.min(rightheight + 1, leftheight + 1)))
                / Double.valueOf(Math.max(leftheight + 1, rightheight + 1));
        // System.out.println("lh,rh,p = " + lh+1 + "," + rh+1 + "," + p*100);
        return percentage * 100;
    }

    public static void save(DSABinarySearchTree objToSave, String fileName) {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;

        try {
            fileStrm = new FileOutputStream(fileName);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(objToSave);

            objStrm.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public DSABinarySearchTree load(String fileName) throws IllegalArgumentException {
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        DSABinarySearchTree inObject = null;

        try {
            fileStrm = new FileInputStream(fileName);
            objStrm = new ObjectInputStream(fileStrm);
            inObject = (DSABinarySearchTree) objStrm.readObject();
            objStrm.close();
        } catch (ClassNotFoundException error) {
            System.out.println(" Class DSALinkedList class not found" + error);

        } catch (Exception error) {
            throw new IllegalArgumentException("Unable to load the object from the file." + error.getMessage());
        }

        return inObject;
    }

    /*************************************************************
     * Name : writeToFileInOrder Import : None Export : None Purpose : to wrap the
     * writeInoder method.
     **************************************************************/

    public void writeToFileInOrder() throws Exception {
        writeInOrder(m_root);
    }

    /***************************************************************************
     * Name : writeInOrder Import : curNode (TreeNode) Export : None Purpose : To
     * write daata into a csv file
     ******************************************************************************/

    private DSAQueueShuffling writeInOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node != null) {
            writeInOrder(node.getLeft()); // First recur on the left side.
            queue.enQueue(node.getKey()); // Store the data inside a queue inOrder

            FileOutputStream fileStrm = null;
            PrintWriter pw;
            // String f;
            try {
                // fileStrm = new FileOutputStream("g.csv");
                // pw = new PrintWriter(fileStrm, true);
                pw = new PrintWriter(new FileOutputStream(new File("writeInorder.csv"), true));
                while (!queue.isEmpty()) {

                    pw.append((String) (queue.deQueue()));
                    pw.append(",");
                }

                pw.close();
            } catch (IOException e) {
                System.out.println("Error in writing to file: " + e.getMessage());

            }

            writeInOrder(node.getRight()); // recur on the right side.

        }

        return queue;
    }

    /*************************************************************
     * Name : writeToFilePreOrder Import : None Export : None Purpose : to wrap the
     * writePreoder method.
     **************************************************************/

    public void writeToFilePreOrder() throws Exception {
        writePreOrder(m_root);
    }

    /***************************************************************************
     * Name : writePreOrder Import : curNode (TreeNode) Export : None Purpose : To
     * write daata into a csv file in PreOrder
     ******************************************************************************/

    private DSAQueueShuffling writePreOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node != null)

        {
            queue.enQueue(node.getKey()); // Store the data inside a queue inOrder

            FileOutputStream fileStrm = null;
            PrintWriter pw;
            // String f;
            try {
                // fileStrm = new FileOutputStream("g.csv");
                // pw = new PrintWriter(fileStrm, true);
                pw = new PrintWriter(new FileOutputStream(new File("writePreOrder.csv"), true));
                while (!queue.isEmpty()) {

                    pw.append((String) (queue.deQueue()));
                    pw.append(",");
                }

                pw.close();
            } catch (IOException e) {
                System.out.println("Error in writing to file: " + e.getMessage());

            }
            writePreOrder(node.getLeft()); // First recur on the left side.
            writePreOrder(node.getRight()); // recur on the right side.

        }

        return queue;
    }

    /*************************************************************
     * Name : writeToFilePostOrder Import : None Export : None Purpose : to wrap the
     * writePostoder method.
     **************************************************************/

    public void writeToFilePostnOrder() throws Exception {
        writePostOrder(m_root);
    }

    /***************************************************************************
     * Name : writePostOrder Import : curNode (TreeNode) Export : None Purpose : To
     * write daata into a csv file in PostOrder
     ******************************************************************************/

    private DSAQueueShuffling writePostOrder(TreeNode node) throws Exception {

        DSAQueueShuffling queue = new DSAQueueShuffling(100);

        if (node != null)

        {
            writePostOrder(node.getLeft()); // First recur on the left side.
            writePostOrder(node.getRight()); // recur on the right side.

            queue.enQueue(node.getKey()); // Store the data inside a queue inOrder

            FileOutputStream fileStrm = null;
            PrintWriter pw;
            // String f;
            try {
                // fileStrm = new FileOutputStream("g.csv");
                // pw = new PrintWriter(fileStrm, true);
                pw = new PrintWriter(new FileOutputStream(new File("writePostOrder.csv"), true));
                while (!queue.isEmpty()) {

                    pw.append((String) (queue.deQueue()));
                    pw.append(",");
                }

                pw.close();
            } catch (IOException e) {
                System.out.println("Error in writing to file: " + e.getMessage());

            }

        }

        return queue;
    }

}
