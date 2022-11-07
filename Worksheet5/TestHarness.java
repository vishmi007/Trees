
/*********************************************************
 * Author: Vishmi Kalansooriya Date: 10th september 2021 Purpose: test the Binary Search tree.
 *********************************************************************************************************/

import java.util.*;
import java.io.*;

public class TestHarness {
    public static void main(String args[]) throws Exception {
        // testBSTMethods();
        // testTraveringInOrder();
        // testTraveringPreOrder();
        // testTraveringPostOrder();
        // testMinMax();
        menu();

    }

    /*******************************************************************************
     * Name : testBSTMethods Import : NONE Export : NONE Purpose : to test the BST
     * methods find, insert and delete. *
     *************************************************************************/
    public static void testBSTMethods() throws IllegalAccessException {
        // To check the insert function
        DSABinarySearchTree tree = new DSABinarySearchTree();
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        try {
            fileStream = new FileInputStream("RandomNames.csv"); // Reading the csv file and storing the Id numbers in
                                                                 // the Binary Search tree.
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;

            // bufRdr.readLine();
            line = bufRdr.readLine();

            while (line != null) {
                lineNum++;
                String[] splitLine;
                splitLine = line.split(",");

                {

                    tree.insert(splitLine[1], splitLine[0]);

                }

                line = bufRdr.readLine();
            }
            fileStream.close();

        } catch (IOException errorDetails) {
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException ex2) {
                }
            }
            System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
            System.out.println("Sorry we could not find the file you are reffering to");
        }

        System.out.println(" Testing the insert method along with the help of the Find method");
        System.out.println("Finind the record Max Plain " + tree.find("Max Plain"));

        System.out.println(" Deleteing the record of Max plain ");
        tree.delete("Max Plain");
        System.out.println(" Testing the delete method along with the help of the Find method");
        System.out.println(tree.find("Max Plain"));

    }

    /*******************************************************************************
     * Name : testTraversingInorder Import : NONE Export : NONE Purpose : to test
     * the inorder traversing method. *
     *************************************************************************/
    public static void testTraveringInOrder() throws Exception {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        // The example in the lecture slide.
        tree.insert("D", "D");
        tree.insert("B", "B");
        tree.insert("C", "C");
        tree.insert("F", "F");
        tree.insert("E", "E");
        tree.insert("G", "G");
        tree.insert("A", "A");

        tree.inOrder();

    }

    /*******************************************************************************
     * Name : testTraversingPreorder Import : NONE Export : NONE Purpose : to test
     * the PreOrder traversing method. *
     *************************************************************************/
    public static void testTraveringPreOrder() throws Exception {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        // The example in the lecture slide.
        tree.insert("D", "D");
        tree.insert("B", "B");
        tree.insert("C", "C");
        tree.insert("F", "F");
        tree.insert("E", "E");
        tree.insert("G", "G");
        tree.insert("A", "A");

        tree.preOrder();
    }

    /*******************************************************************************
     * Name : testTraversingPostorder Import : NONE Export : NONE Purpose : to test
     * the PostOrder traversing method. *
     *************************************************************************/
    public static void testTraveringPostOrder() throws Exception {
        DSABinarySearchTree tree = new DSABinarySearchTree();

        // The example in the lecture slide.
        tree.insert("D", "D");
        tree.insert("B", "B");
        tree.insert("C", "C");
        tree.insert("F", "F");
        tree.insert("E", "E");
        tree.insert("G", "G");
        tree.insert("A", "A");

        tree.postOrder();

    }

    /*******************************************************************************
     * Name : testMinMax Import : NONE Export : NONE Purpose : to test the method
     * minNode, maxNode,height and balance methods. *
     *************************************************************************/
    public static void testMinMax() throws IllegalAccessException {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        tree.insert("4", 50);
        tree.insert("5", 14);
        tree.insert("8", 68);
        tree.insert("2", 7);
        tree.insert("3", 80);
        tree.insert("7", 100);

        System.out.println("The maximum key of the binary search tree is : " + tree.maxNode());
        System.out.println("The minimum key of the binary search tree is : " + tree.minNode());
        System.out.println("The height of the binary search tree is      : " + tree.height());
        System.out.println("A percentage score for how balanced the tree is " + tree.balanced() + " % ");

    }

    /*******************************************************************************
     * Name : menu Import : NONE Export : NONE Purpose : to print a menu and do
     * tasks according to the user's choice *
     *************************************************************************/

    public static void menu() throws Exception {
        Scanner sc = new Scanner(System.in);
        DSABinarySearchTree tree = new DSABinarySearchTree();

        int input;
        String fileName = " ";
        int inputDisplay = 0;
        System.out.println("Hello ! Please enter a number to choose any of the options below .");
        System.out.println(" 1. read a csv File \n 2. read a serialized file \n 3. Display the tree ");
        System.out.println(" 4. Write a csv file \n 5. Write a serialized file ");

        input = sc.nextInt();

        switch (input) {
            case 1: {
                System.out.println(" Please enter the name of the file that is to be read");
                fileName = sc.next();
                readCSV(fileName);
                break;
            }

            case 2:
                // System.out.println(" Please enter the name of the serialized file");
                tree.load("writeToFile.xml");

                break;

            case 3:
                System.out.println(" In which order would you like to display the tree ?");
                System.out.println(" 1. inOrder \n 2. preOder \n  3. PostOrder");
                inputDisplay = sc.nextInt();
                displayTree(inputDisplay);
                break;

            case 4:
                System.out.println(" In which order would you like to write the tree  to the file?");
                System.out.println(" 1. inOrder \n 2. preOder \n 3. PostOrder");
                inputDisplay = sc.nextInt();
                writeTocsv(inputDisplay);
                break;
            case 5:
                writeSerializedFile();
                break;

        }

    }

    /*******************************************************************************
     * Name : readCSV Import : NONE Export : NONE Purpose : to read a CSV file *
     *************************************************************************/
    public static void readCSV(String pFileName) throws IllegalAccessException {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        try {
            fileStream = new FileInputStream(pFileName); // Reading the csv file and storing the Id numbers in the
                                                         // binary search tree.
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;

            // bufRdr.readLine();
            line = bufRdr.readLine();

            while (line != null) {
                lineNum++;
                String[] splitLine;
                splitLine = line.split(",");

                {

                    tree.insert(splitLine[1], splitLine[0]);

                }

                line = bufRdr.readLine();
            }
            fileStream.close();

        } catch (IOException errorDetails) {
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException ex2) {
                }
            }
            System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
            System.out.println("Sorry we could not find the file you are reffering to");

        }

        System.out.println(" Proof that the file is read");
        System.out.println("Finding the first persons Id number");
        System.out.println("The id number of Sofia Bonfilgo is " + tree.find("Sofia Bonfiglio"));

    }

    /*******************************************************************************
     * Name : display Import : NONE Export : NONE Purpose : to display the tree
     * according to the user's choice in inorder/preorder/postorder *
     *************************************************************************/
    public static void displayTree(int theOrder) throws Exception {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        try {
            fileStream = new FileInputStream("Names.csv"); // Reading the csv file and storing the Id numbers in the
                                                           // binary search tree.
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            lineNum = 0;

            // bufRdr.readLine();
            line = bufRdr.readLine();

            while (line != null) {
                lineNum++;
                String[] splitLine;
                splitLine = line.split(",");

                {

                    tree.insert(splitLine[1], splitLine[0]);

                }

                line = bufRdr.readLine();
            }
            fileStream.close();

        } catch (IOException errorDetails) {
            if (fileStream != null) {
                try {
                    fileStream.close();
                } catch (IOException ex2) {
                }
            }
            System.out.println("Error in fileProcessing: " + errorDetails.getMessage());
            System.out.println("Sorry we could not find the file you are reffering to");

        }

        switch (theOrder) {
            case 1:
                System.out.println(" The names are printed in ascending order");
                tree.inOrder();
                break;

            case 2:
                tree.postOrder();
                break;

            case 3:
                tree.preOrder();
                break;

        }

    }

    /*******************************************************************************
     * Name : writeTOcsv Import : NONE Export : NONE Purpose : to write a tree to a
     * csv in inorder/postorder/preorder *
     *************************************************************************/
    public static void writeTocsv(int theOrder) throws Exception {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        // The example in the slide.
        tree.insert("D", "D");
        tree.insert("B", "B");
        tree.insert("C", "C");
        tree.insert("F", "F");
        tree.insert("E", "E");
        tree.insert("G", "G");
        tree.insert("A", "A");

        switch (theOrder) {
            case 1:
                tree.writeToFileInOrder();
                break;
            case 2:
                tree.writeToFilePreOrder();
                break;
            case 3:

                tree.writeToFilePostnOrder();
                break;

        }

    }

    /*******************************************************************************
     * Name : writeSerializedFile() Import : NONE Export : NONE Purpose : to write
     * tree objects. *
     *************************************************************************/
    public static void writeSerializedFile() throws IllegalAccessException {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        tree.insert("D", "D");
        tree.insert("B", "B");
        tree.insert("C", "C");
        tree.insert("F", "F");
        tree.insert("E", "E");
        tree.insert("G", "G");
        tree.insert("A", "A");

        tree.save(tree, "write.xml");

    }

}
