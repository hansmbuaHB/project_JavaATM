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
// attributes 


public class Withdrawal extends Transaction {
    
   // private int accountNumber;// acount to withdraw fons from
    private double amount ; // amount to withdraw
    
    // references to associated objects
  //  private Screen screen; // ATM's screen 
    private Keypad keypad;// ATM's keypad
    private CashDispenser cashdispenser;// ATM's cash dispenser
   // private BankDatabase bankDatabase; // account infor database
    
    
           
    
    public Withdrawal()
    {
        
    }
    @Override 
    public void execute()
    {
        
    }
    
}
