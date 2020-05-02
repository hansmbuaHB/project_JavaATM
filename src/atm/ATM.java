/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author HANS
 */
public class ATM {
    private boolean userAuthenticated; // whether user is authenticated 
    private int currentAccountNumber; // current user Account number 
    private Screen screen; // ATM Screen 
    private Keypad keypad; // ATM keypad
    private CashDispenser cashDispenser; // ATM's cashDispenser 
    private BankDatabase bankDatabase; // account information database
    private DepositSlot depositSlot;
    
    // constants corrensponding to main menu options 
    private static final int BALANCE_INQUITRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    public ATM()
    {
        userAuthenticated = false; // user us not authenticated to start 
        currentAccountNumber = 0; // no current account number to start
        screen = new Screen(); // creat screen
        keypad = new Kaypad();// creat keypad
       depositSlot = new DepositSlot();//creat depositslot
       bankDatabase = new BankDatabase();//creat acct info database
     
    }
    // start ATM
    public void run()
    {
        // welcome and authenticate users ; perform transactions
        while(true)
        {
            //loop while user is not yet authenticated 
            while(!userAuthenticated){
                screen.displayMessageLine("\nWelcome !");
                authenticateUser(); // authenticate user
            }
            
            performTransactions();// user is now authentitated
            userAuthenticated = false; // reset before next ATM session
            currentAccountNumber = 0; // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye !");
            
        }// end of while
            
    }// end of run 
    
    private void authenticateUser(){
        
        screen.displayMessage("\nPlease enter your account number : ");
        int accountNumber = keypad.getInput();// input account number 
        screen.displayMessage("\nEnter your PIN : ");//promt for PIN
        int pin = keypad.getInput();// input pin 
        
        // set userAuthenticate to boolean value returned by database
        
        userAuthenticated = bankDatabase.authenticateUser(accountNumber , pin);
        
        // check whether authentication succeeded 
         if(userAuthenticated)
         {
             currentAccountNumber = accountNumber; // save user's account #
         }// end if 
          else
        screen.displayMessageLine("Invalid Account Number or PIN. please try again ");
        
    }// end authenticateuser method 
    private void performTransactions()
    {
        Transaction currentTransaction = null;
        
        boolean userExited = false; // user has not chosen to exit
        // loop while user has not yet chosen option to exit system
        while(!userExited)
        {
            // show main menu and get user selection 
            int mainMenuSelection = displayMainMenu();
            // decide how to proceed based on user's menu selection
            switch(mainMenuSelection)
            {
                //user chose to perform one of the three transaction types 
                case BALANCE_INQUITRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    //initialize as new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute(); // execut transaction
                   break;
                    
                case EXIT: // user chose to terminate session 
                    screen.displayMessageLine("\nExiting the System ...");
                    userExited = true;
                    break;
                default: // user did not enter integer from 1 to 4;
                    screen.displayMessageLine("\nYou did not enter a valid selsection. try again. ");
                    break;
                
            }// end switch
        }//end while 
    }// end method performTransaction
   
    // display the main menu and return an input selection
    private int displayMainMenu()
    {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine( "1 - View my balance ");
        screen.displayMessageLine( "2 - Withdraw Cash ");
        screen.displayMessageLine( "3 - Deposit funds ");
        screen.displayMessageLine( "4 - Exit\n");
        screen.displayMessageLine( "Enter a choice: ");
        return keypad.getInput();// return user selection
    }// end disolayMainMenu
    
       // return Object of specified transaction subclass
    private Transaction createTransaction(int type)
    {
        Transaction temp = null;// temporary transaction variable 
        
        switch(type){
            case BALANCE_INQUITRY:// creat a new balanceInquiry transaction
                temp = new BalanceInquiry( currentAccountNumber, screen,bankDatabase,keypad,cashDispenser);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccountNumber,screen,bankDatabase,keypad,cashDispenser);
                break;
            case DEPOSIT:// creat new Withdrawal transaction
                temp = new Deposit(currentAccountNumber, screen, bankDatabase,keypad,depositSlot);
                break;
           
        }// end switch 
        return temp;
}
    
}
