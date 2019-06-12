package lastplease;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Node {
    Human data; //PARAMETER FOR HUMAN CLASS (ID, REVENUE, NAME)
    Node parent; //PARAMETER FOR PARENT
    private ArrayList<Node> children; //DECLARE CHILD NODE

    //MAIN CALL HERE
    public Node(Human data) {
        this (data, null); //PASS TO CONSTRUCTOR (ID, REVENUE, NAME, PARENT)
    }

    //CONSTRUCTOR
    public Node(Human data, Node parent) {
        this.data = data;
        this.parent = parent;
        children = new ArrayList<>(); //INITIALIZER TO CREATE CHILD NODE
    }
    
    //METHOD TO ADD USER
    public void addUser (Node root, Human parent, Human child) {
        iter (root, 0, parent, child); //DEPTH 0 UNTUK BOSS
    }
    
    //UNTUK TRAVERSE EVERY NODES
    public static void iter (Node node, int depth, Human parent, Human child) {
        if (node.data.ID == parent.ID) { //CHECK ID SAMA TAK DENGAN PARENT
            node.addChild(child, node);
        }
        for (Node nodes:node.getChildren()) { //RECURSION. CHECK SETIAP PARENT
            iter (nodes, depth+1, parent, child);
        }
    }
    
    public boolean addChild (Node node) {
        return children.add(node);
    }
    
    //METHOD TO ADD USER
    public boolean addChild(Human data, Node parent) {
        return addChild (new Node(data, parent)); //CREATE NEW CHILD NODE
    }
    
    public ArrayList<Node> getChildren() {
        return new ArrayList<>(children);
    }
    
    //METHOD TO PRINT TREE FOR ADMIN
    public static void print(Node node, int depth) {
        for (int i=0; i<depth; i++) {
            System.out.print("        ");
        }
        if (node.data.ID == 0) {
            System.out.println(node.data.userName + " | " + node.data.companyRevenue);
        }
        else {
            System.out.println("ID - " + node.data.ID + " " + node.data.userName + " | " + node.data.userRevenue);
        }
        
        for (Node nodes:node.getChildren()) { //RECURSION
            print(nodes, depth+1);
        }
    }
    
    //PRINT TREE
    public static void printData(Node root) {
        print(root, 0);
    }
    
    //METHOD TO SAVE & WRITE DATA TO TEXT FILE
    public static void saveData(Human data) {
            try {
                PrintWriter p = new PrintWriter(new FileOutputStream("Data", true));
                if (data.ID == 0) { //FOR BOSS
                    p.println();
                    p.write("- - - - - - - - - - - - - - - - - - - ");
                    p.println();
                    p.println();
                    p.write("DREAM CORPORATION");
                    p.println();
                    p.write("Revenue : RM" + data.companyRevenue);
                    p.println();
                }
                else {
                    p.println();
                    p.write("ID : " + data.ID);
                    p.println();
                    p.write("Name : " + data.userName);
                    p.println();
                    p.write("Revenue : " + data.userRevenue);
                    p.println();
                }
                p.close();
            }
            catch (Exception e) {
                System.out.println("Not found");
            }
    }
        
    //MEHTOD TO CLEAR TEXTFILE BEFORE ADD THE UPDATED ONE
    public static void clearFile() {
        File file = new File("Data.txt"); 
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            }
            catch (Exception e) {
                System.out.println("Not found");
            }
        }
    }
    
    //METHOD TO SAVE FILE
    public static void saved(Node node) {
        clearFile();
        saveData(node.data);
        
        for (Node child: node.getChildren()) {
            saved(child);
        }
    }
    
//    //METHOD TO CALCULATE REVENUE
//    public void pay (Node node, Node child, Human data, int depth) {
//        double revenue;
//        
//        if (node.data.ID == 0) { //COMPANY REVENUE
//            revenue = node.data.companyRevenue + (50*(20/100));
//        }
//        else if (node.parent.data.ID == 0) { //REVENUE 1ST GENERATION
//            revenue = node.data.userRevenue + (50*(3/100));
//            revenue(revenue, child, node.parent.data.ID, depth);
//        }
//        else if (node.parent.parent.data.ID == 0) { //REVENUE 2ND GENERATION
//            revenue = node.data.userRevenue + (50*(6/100));
//            revenue(revenue, child, node.parent.parent.data.ID, depth);
//        }
//        else if (node.parent.parent.parent.data.ID == 0) { //REVENUE 3RD GENERATION
//            revenue = node.data.userRevenue + (50*(9/100));
//            revenue(revenue, child, node.parent.parent.parent.data.ID, depth);
//        }
//        else if (node.parent.parent.parent.parent.data.ID == 0) { //REVENUE 4TH GENERATION
//            revenue = node.data.userRevenue + (50*(12/100));
//            revenue(revenue, child, node.parent.parent.parent.parent.data.ID, depth);
//        }
//        else {
//            if (node.parent.parent.parent.parent.parent.data.ID == 0) { //REVENUE 5TH GENERATION
//                revenue = node.data.userRevenue + (50*(50/100));
//                revenue(revenue, child, node.parent.parent.parent.parent.parent.data.ID, depth);
//            }
//        }
//        for(Node nodes:node.getChildren()) {
//           pay(nodes, child, data, depth+1);
//        }        
//    }
//    
//    public void revenue (double revenue, Node node, int userID, int depth) {
//        
//        for (Node nodes:node.getChildren()) { //RECURSION. CHECK SETIAP PARENT
//            revenue (revenue, node, userID, depth+1);
//        }
//    }
//    
//    public void userPay (Node root, Human data) {
//        Node child = null;
//        pay (root, child, data, 0);
//    }    
    
    //METHOD TO DELETE USER
    public static void deleteUser() {
        
    }
}
