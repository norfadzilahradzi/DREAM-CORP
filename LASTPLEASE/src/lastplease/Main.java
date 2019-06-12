package lastplease;

import java.awt.Component;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {
    public static int ID = 10;
    public static int regFee = 50;
    public static Human boss = new Human (0,0,"Boss"); //CREATE BOSS NODE
    public static Human[] totalHuman = new Human[1000]; //ONLY 1000 CHILD NODES CAN BE INSERTED
    public static Node node = new Node(boss);

    public static void main(String[] args) {
        Component frame = null;
        JOptionPane.showMessageDialog(frame, "We serve you the best service in Malaysia", "DreamCorp.co", JOptionPane.PLAIN_MESSAGE);

        
        SetUser();
        Starting();
    }
    
    public static void SetUser() {
        String[] existingUser = {"Fadzilah", "Amirah", "Arina", "Yunseong", "Yohan", "Minhee", "Daniel", "Hyunbin", "Ahmad"}; 
        for (int i=1; i<10; i++) {
            totalHuman[i] = new Human(i, regFee, existingUser[i-1]); //1ST USER WILL GET ID=1
            totalHuman[i].userPassword(existingUser[i-1]); //USER PASSWORD IS THEIR NAMES
        }
        //NODE, PARENT, CHILD
        node.addUser(node, boss, totalHuman[1]);
        node.addUser(node, boss, totalHuman[2]);
        node.addUser(node, boss, totalHuman[7]);
        node.addUser(node, totalHuman[1], totalHuman[3]);
        node.addUser(node, totalHuman[2], totalHuman[5]);
        node.addUser(node, totalHuman[2], totalHuman[6]);
        node.addUser(node, totalHuman[7], totalHuman[9]);
        node.addUser(node, totalHuman[3], totalHuman[4]);
        node.addUser(node, totalHuman[4], totalHuman[8]);
        
//        node.userPay(node, totalHuman[1]);
//        node.userPay(node, totalHuman[2]);
//        node.userPay(node, totalHuman[7]);
//        node.userPay(node, totalHuman[3]);
//        node.userPay(node, totalHuman[5]);
//        node.userPay(node, totalHuman[6]);
//        node.userPay(node, totalHuman[9]);
//        node.userPay(node, totalHuman[4]);
//        node.userPay(node, totalHuman[8]);
    }
    
    public static void Starting() {
        Scanner s = new Scanner(System.in);
        System.out.println("WELCOME TO DREAM CORPORATION COMPANY.");
        System.out.print("Who are you [ADMIN/USER]? : ");
        String user = s.next();
        
        //ADMIN SECTION
        if (user.equalsIgnoreCase("Admin")) {
            System.out.print("Enter KEY : ");
            int KEY = s.nextInt();
            if (KEY == 127) {
                Admin();
            }
            else {
                for (int i=1; i<3; i++) { //GET 2 ATTEMPTS TO TRY AGAIN
                    System.out.print("Enter KEY again (2 attempts) : ");
                    KEY = s.nextInt();
                    if (KEY == 127) {
                        Admin();
                        break;
                    }                 
                    else {
                        System.out.print("Enter KEY again (last attempt) : ");
                        KEY = s.nextInt();
                        if (KEY == 127) {
                            Admin();  
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }
        
        //USER SECTION
        else {
            if (user.equalsIgnoreCase("User")) {
                
                String num1, num2, number1;
		int number2, sum;
		
                num2 = JOptionPane.showInputDialog("ID");
		number2 = Integer.parseInt(num2);
                
		num1 = JOptionPane.showInputDialog("Password");
                number1 = String.valueOf(num1);
			
                if (totalHuman[number2] != null) {
                    if (number2 != 0) {
                        if (number1.equals(totalHuman[number2].userPassword)) {
                            User(number2);
                        }
                    }
                }
                else {
                    System.out.println("User not exist.\n");
                    Starting();
                }
            }
        }
    }
    
    //AVAILABLE ADMIN OPTIONS
    public static void Admin() {
        Scanner admin = new Scanner(System.in);
        System.out.println("\nSelect your option!");
        System.out.println("\t1. SEE TREE\n" + 
                           "\t2. CREATE USERS\n" +
                           "\t3. DELETE USERS\n" +
                           "\t4. RETRIEVE USERS\n" +
                           "\t5. COMPANY REVENUE\n" +
                           "\t6. ABOUT REGISTRATION FEE");
                        
        int choice;
        do {
            System.out.print("\nMy choice [Press '0' to exit] : ");
            choice = admin.nextInt();
            
            if (choice == 1) {
                Node.printData(node);
            }
            else if (choice == 2) {
                
                String num1, num2, number1, number2;
                num2 = JOptionPane.showInputDialog("Name");
                number2 = String.valueOf(num2);
		num1 = JOptionPane.showInputDialog("Password");
                number1 = String.valueOf(num1);
                
                totalHuman[ID] = new Human(ID, regFee, number2); //CREATE NEW USER
                node.addUser(node, boss, totalHuman[ID]);
                //PAY METHOD
                
                totalHuman[ID].userPassword(number1);
                System.out.println("User was successfully added.");
                ID++; //INCREMENT FOR TOTAL USERS 
                node.saved(node);
                
            }
            else if (choice == 3) {
                //DELETE USER
            }
            else if (choice == 4) {
                
                String num2;
		int number2;
                num2 = JOptionPane.showInputDialog("ID");
		number2 = Integer.parseInt(num2);
                
                    if (totalHuman[number2] != null) {
                        System.out.print("\t1 - Revenue\n" +
                                         "\t2 - Encrypt name\n" +
                                         "\t3 - Decrypt name\n" +
                                         "\tChoose : ");
                        int adminChoose = admin.nextInt();
                        if (adminChoose == 1) {
                            System.out.println("\tUser " + number2 + " revenue : RM" + totalHuman[number2].userRevenue);
                        }
                        else if (adminChoose == 2) {
                                System.out.println("\tEncrypted name : " + totalHuman[number2].userEncrypt(totalHuman[number2].userName));
                        }
                        else {
                            if (adminChoose == 3) {
                                System.out.println("\tDecrypted name : " + totalHuman[number2].userName);
                            }
                        }
                    }                
            }
            else if (choice == 5) {
                System.out.println("Dream Corporation Revenue : RM" + boss.companyRevenue);
            }
            else {
                if (choice == 6) {
                    System.out.println("Current registration fee is : RM" + regFee);
                    System.out.print("Do you want to make any changes [Yes/No]? ");
                    String yesNo = admin.next();
                    if (yesNo.equalsIgnoreCase("Yes")) {
                        System.out.print("Change to : RM");
                        int newRegFee = admin.nextInt();
                        regFee =  newRegFee;
                        System.out.println("Success!");
                    }
                    else {
                        if (yesNo.equalsIgnoreCase("No")) {
                        }
                    }
                }
            }
//            node.save(node);
        }
        while (!(choice == 0));
    }
    
    //AVAILABLE USER OPTIONS
    public static void User(int myID) {
        Scanner user = new Scanner(System.in);
        System.out.println("\t1 - Add user\n" +
                           "\t2 - Encrypted name\n" +
                           "\t3 - Revenue\t");
        int userChoice;
        do {
            System.out.print("My choice [0 to exit] :");
            userChoice = user.nextInt();
            
            if (userChoice == 1) {
                
                String num1, num2, number1, number2;
                num2 = JOptionPane.showInputDialog("Name");
                number2 = String.valueOf(num2);
		num1 = JOptionPane.showInputDialog("Password");
                number1 = String.valueOf(num1);
                
                totalHuman[ID] = new Human(ID, regFee, number2); //CREATE NEW USER
                node.addUser(node, boss, totalHuman[ID]);
                //PAY METHOD
                
                totalHuman[ID].userPassword(number1);
                System.out.println("User was successfully added.");
                ID++; //INCREMENT FOR TOTAL USERS 
                node.saved(node);
            }
            else if (userChoice == 2) {
                System.out.println("Encrypted name : " + totalHuman[myID].userEncrypt(totalHuman[myID].userName));
            }
            else {
                if (userChoice == 3) {
                    System.out.println("Your revenus is : RM" + totalHuman[myID].userRevenue);

                }
            }
            System.out.println();
        }
        while (!(userChoice == 0));
    }
    
    
//    private static void placeComponents(JPanel panel) {
//        panel.setLayout(null);
//        JLabel userLabel = new JLabel("Admin");
//        userLabel.setBounds(10,20,80,25);
//        panel.add(userLabel);
//
//        JTextField userText = new JTextField(20);
//        userText.setBounds(100,20,165,25);
//        panel.add(userText);
//
//        JLabel passwordLabel = new JLabel("Password");
//        passwordLabel.setBounds(10,50,80,25);
//        panel.add(passwordLabel);
//
//        JPasswordField passwordText = new JPasswordField(20);
//        passwordText.setBounds(100,50,165,25);
//        panel.add(passwordText);
//
//        JButton loginButton = new JButton("login");
//        loginButton.setBounds(10, 80, 80, 25);
//        panel.add(loginButton);
//    }
    
}
    