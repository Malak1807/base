import java.util.*;

/**
 *
 * @author Malak Sohaib Salah
 * Date: 12/2/2025
 * Note that this program had to be restricted to the following java topics:
 * Elementary Programming - Selections - Mathematical Functions - Strings - Characters - Loops - Methods - Arrays  
 */
public class BankSystem {
    public static void main(String[]args){
        //Creates Scanner object 
        Scanner input = new Scanner (System.in);
        
        //Creates an array to list account numbers of users
        int[] accountNumbers = new int[100];
        //Creates an array to list passwords of users
        String[] passwords = new String[100];
        //Creates an array to list the amount of money in the balance of users
        double[] balance = new double[100];
        
        //Initializes variables to be used in the program
        int index = -1;
        String  nextUser = "yes";
        int count = 0;
        
        //Loop that gets executed every time a user wants to use the bank system
        while(nextUser.equals("yes")){
          //Asks the user if he/she is new or not
          System.out.print("Do you have a bank account? (enter yes or no): ");
          String account = (input.next().toLowerCase()).trim();
        
          if (account.equals("no") && count<100){
            index++;
            count++;
            
            //Asks new user for basic information
            System.out.println("\nEnter your first name: ");
            String name = input.next();
            
            System.out.println("Enter your gender: ");
            String gender = input.next();
            
            System.out.println("Enter your telephone number: ");
            int telephoneNumber = input.nextInt();
            
       
            //Generates a unique random account number for new user
            do{
                accountNumbers[index] = (int)(Math.random()*1000000);
            }while(takenAccountNumber(accountNumbers, index));
            
            
            //Prints account number for the user to see
            System.out.println("\nYour account number is: " + accountNumbers[index]);
            
            
            //Loop that asks new user to create password and ensures it is valid and unique
            do{
            //Prompts user to create and enter a password that abides with the displayed rules
            System.out.println("\nCreate a password: ");
            System.out.println("1. Must have at least 7 characters\n2. Must have at least one capital letter\n3. Must have at least one number");
            passwords[index] = input.next();
            
            //Prints the appropriate comment based on type of flaw if there is one 
            if(!uniquePassword(passwords, index))
                System.out.println("Password taken. Try again.");
            
            else if(!validPassword(passwords[index]))
                System.out.println("Please abide by the password rules.");
            
            }while(!uniquePassword(passwords, index) || !validPassword(passwords[index]));
          }
          
          /*Login that uses a loop to ensure the correctness of account number 
          and password entered by the user*/
          System.out.println("\nEnter your account number and password to login");
          //Variable to be used for condition in if-statement
          int count2 = 0;
          //Variable that saves the account number entered by user 
          int aN;
          //Variable that saves the password entered by user 
          String p;
          //Variable that saves the index of the entered account number found in accountNumbers array
          int a;
          //Variable that saves the index of the entered password found in passwords array
          int b;
          do{
          //if count2>0 is true at this point it means user entered wrong log in credentials
          if (count2>0){
              System.out.println("Incorrest username or password.");
              System.out.println("Try again.");
          }
          
          //Prompts user to enter their log in credentials 
          System.out.print("Account Number: ");
          aN = input.nextInt();
          
          System.out.print("Password: ");
          p = input.next();
          
          //initiales a and b to -1
          a = -1;
          b = -1;
          
          /*for loop that checks which index in accountNumbers array has the value that matches
          the value entered by user and saves it*/
          for(int i = 0; i < accountNumbers.length ; i++){
              if(aN == accountNumbers[i]){
                  a = i;
                  break;
              }
          }
          /*for loop that checks which index in passwords array has the string that matches
          the string entered by user and saves it*/
          for(int i = 0; i < passwords.length ; i++){
              if(p.equals(passwords[i])){
                  b = i;
                  break;
              }
          }
          //increments count2 by 1. count2 signifies if the loop has been executed at least once
          count2++;
         }while(a == -1 || b == -1 || a!=b );
          
        // Initializes variables to be used 
        double deposit, transfer;
        double withdraw = 0;
        int beneficiaryAccountNumber, n, c;
        String newTransaction = "no";
        
        /*do-while loop that performs the banking operation chosen by the user and repeats every
        time an invalid choice number is written by the user*/
          do{
              //calls the method menu
              menu();
              //Prompts user to enter their choice of banking operation
              System.out.print("Enter the number adjacent to your choice: ");
              n = input.nextInt();
              //Switch statement that performs a banking operation based on user input saved in n
              switch (n) {
                  //Shows balaance
                  case 1:
                      System.out.println("Your balance is: " + balance[a]);
                      break;
                      
                  //Deposits money to account, the ammount is specified by the user
                  case 2:
                      do{
                          System.out.print("Enter deposit ammount: ");
                          deposit = input.nextDouble();
                          if (deposit<0)
                              System.out.println("Invalid amount. Try again.");
                      }while(deposit < 0);
                      
                      balance[a] += deposit;
                      
                      System.out.println("Deposit completed successfully.");
                      break;
                      
                  /*Withdraws money from account, the ammount is specified by the user. 
                    Asks for amount again if entered amount is greater than amount in balance.*/
                  case 3:
                      do {
                          System.out.print("Enter withdraw ammount: ");
                          withdraw = input.nextDouble();
                          
                          if (withdraw > balance[a]) 
                              System.out.println("Insufficient balance.");
                          
                          else if (withdraw < 0)
                              System.out.println("Invalid amount. Try agsin.");
                          
                      } while (withdraw > balance[a] || withdraw < 0);
                      
                      balance[a] -= withdraw;
                      
                      System.out.println("Withdrawal completed successfully");
                      break;
                      
                  /*Transfers money to another account, lets user specify the amount.
                     Keeps asking for number of account to transfer to until it is found in 
                      accountNumbers array */
                  case 4:
                      do {
                          System.out.print("Enter the accout number of the beneficiary: ");
                          beneficiaryAccountNumber = input.nextInt();
                          for (c = 0; c < accountNumbers.length; c++) {
                              if (beneficiaryAccountNumber == accountNumbers[c]) {
                                  break;
                              }
                          }
                          if(c == accountNumbers.length)
                              System.out.println("Invalid account number.");
                      } while (c == accountNumbers.length);
                      
                      do {
                          System.out.print("Enter transfer amount: ");
                          transfer = input.nextDouble();
                          
                          if (transfer > balance[a]) 
                              System.out.println("Insufficient balance.");
                          else if (transfer < 0 )
                              System.out.println("Invalid amount. Try again.");
                          
                      } while (transfer > balance[a] || transfer < 0);
                      
                      balance[a] -= transfer;
                      balance[c] += transfer;
                      
                      System.out.println("Transfer completed successfully.");
                      break;
                      
                  //Deletes the account of the user after confirming with the user
                  case 5:
                      String confirmation;
                      System.out.println("Are you sure you want to delete your account? (enter yes or no) ");
                      confirmation = (input.next().toLowerCase()).trim();
                      if (confirmation.equals("yes")) {
                          accountNumbers[a] = 0;
                          passwords[a] = "DELETED";
                          System.out.println("Account deleted.");
                      }
                      break;
                      
                  default:
                      System.out.println("\nInvalid choice number.");
                      break;
              }
              if(n >= 1 && n <= 5){
                System.out.println("Do you want to perform a new transaction? (enter yes or no)");
                newTransaction = (input.next().toLowerCase()).trim();
              }
        }while(n > 5 || n < 1 || newTransaction.equals("yes"));
         
          //Asks if there is a new customer
         System.out.print("Is there a next user? (enter yes or no): ");
         nextUser = (input.next().toLowerCase()).trim();
          
        }
    }
    
    /*Method that checks whether the account number entered by the user exists or not
    and returns a boolean value of true if yes and false if no*/
    public static boolean takenAccountNumber(int[] x, int i){
        boolean returnValue = false;
        for(int n = 0; n < i ; n ++ ){
            if (x[i]==x[n]){
                returnValue = true; 
                break;
            }
        }
        return returnValue;
        
    }
    
    /*Method thay checks if the password created by a new user is not taken by another user
    returns a boolean value of true if not taken and false if taken */
    public static boolean uniquePassword(String[] p, int i){
        boolean unique = true;
        for(int n = 0; n < i ; n++){
            if(p[i].equals(p[n])){
                unique = false;
                break;
            }
        }
        return unique;
    }
    
    /*Method that checks if the password entered by the user abides with the password rules or not
    and returns a boolean value of true if yes and false if no*/
    public static boolean validPassword (String p){
        boolean length = false;
        boolean capitalLetter = false;
        boolean digit = false;  
        
        //checks if the password has at least 7 characters
        if (p.length()>=7)
            length = true; 
        
        //checks if there is at least one capital letter in the password
        for(int i = 0 ; i < p.length() ; i++){
            if(Character.isUpperCase(p.charAt(i))){
                capitalLetter = true; 
                break;
            }
        }
        
        //checks if there is at least one number in the password
        for(int i = 0 ; i < p.length() ; i ++){
            if(Character.isDigit(p.charAt(i))){
                digit = true;
                break;
            } 
        }
        
        if(length && capitalLetter && digit)
            return true; 
        else
            return false;
        
    }
    
    //Method that displays banking operations 
    public static void menu(){
         System.out.println("\nMENU:\n1.View balance\n2.Deposit money\n3.Withdraw money\n4.Transfer money to another existing account\n5.Delete account");
    }
}
