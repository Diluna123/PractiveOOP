package bankAccountSystem;

import java.util.AbstractCollection;
import java.util.ArrayList;

abstract class Account {

    private int accoutNumber;
    private String accountHolderName;
    private double balance;
    private boolean isActive;

    public Account(int accoutNumber, String accountHolderName, double balance, boolean isActive) {
        this.accoutNumber = accoutNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.isActive = isActive;
    }

    public int getAccoutNumber() {
        return accoutNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    void displayAccountDetail(){
        System.out.println("Account Number :"+ getAccoutNumber());
        System.out.println("Account Holder :"+ getAccountHolderName());
        System.out.println("Account Balance :"+ getBalance());
        System.out.println("Account Type :"+ accountType());

        System.out.println("Account Status :"+ isActive());
    }

    abstract String accountType();
    abstract double interestRate();

}
class SavingAccount extends Account{

    public SavingAccount(int accoutNumber, String accountHolderName, double balance, boolean isActive) {
        super(accoutNumber, accountHolderName, balance, isActive);
    }

    @Override
    String accountType() {
        return "Saving Account";
    }

    @Override
    double interestRate() {
        return 0.05;
    }
}
class CurrentAccount extends Account{
    public CurrentAccount(int accoutNumber, String accountHolderName, double balance, boolean isActive) {
        super(accoutNumber, accountHolderName, balance, isActive);
    }

    @Override
    String accountType() {
        return "Current Account";
    }

    @Override
    double interestRate() {
        return 0.02;
    }
}
class FixedDipositAccount extends Account{
    public FixedDipositAccount(int accoutNumber, String accountHolderName, double balance, boolean isActive) {
        super(accoutNumber, accountHolderName, balance, isActive);
    }

    @Override
    String accountType() {
        return "Fixed Deposit";
    }

    @Override
    double interestRate() {
        return 0.10;
    }
}

interface BankService{
    void createAccount(Account account);
    void searchAccount(int accountNumber);
//    void withdraw(int accountNumber, int amount);
    void removeAccount(int accountNumber);
//    void deposit(int acNumber, double amount);
}

class Bank implements BankService{

    ArrayList<Account> accounts= new ArrayList<>();





    @Override
    public void createAccount(Account account) {
        accounts.add(account);
        System.out.println("Account Created Success!");

    }

    private Account findAccount(int accountNumber){
        for(Account ac: accounts){
            if(ac.getAccoutNumber() == accountNumber ){
                return ac;
            }
        }
        throw new IllegalArgumentException("Wrong Account Number");

    }

    @Override
    public void searchAccount(int accountNumber) {
        Account ac= findAccount(accountNumber);
        ac.displayAccountDetail();

    }

//    @Override
//    public void withdraw(int accountNumber, int amount) {
//        Account ac = findAccount(accountNumber);
//        if(ac.accountType().equals("Fixed Deposit")) System.out.println("Cant withdraw money from fixed Deposit Account");
//        else if(ac.getBalance() <= amount){
//            throw new IllegalArgumentException("No enough money in your Account ");
//
//        }else{
//             double newBalance = ac.getBalance() - amount;
//             ac.setBalance(newBalance);
//            System.out.println("Withdraw Success | Your Current Balance is : Rs. "+ ac.getBalance());
//
//
//        }
//
//    }

    @Override
    public void removeAccount(int accountNumber) {
        Account ac = findAccount(accountNumber);

            ac.setActive(false);
            System.out.println("Account Removed");

    }


//    @Override
//    public void deposit(int acNumber, double amount) {
//        Account ac = findAccount(acNumber);
//        if(!(amount < 0)){
//            ac.setBalance(ac.getBalance()+amount);
//            System.out.println("Money Deposit Success");
//        }
//
//    }
}

class Transection{
    private double deposit;
    private double withdrawal;
    private double interest;
    private double transactionFee;
    private double finalBalance;

    public Transection(double deposit, double withdrawal, double interest, double transactionFee, double finalBalance) {
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.interest = interest;
        this.transactionFee = transactionFee;
        this.finalBalance = finalBalance;
    }

    public double getDeposit() {
        return deposit;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public double getInterest() {
        return interest;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public double getFinalBalance() {
        return finalBalance;
    }
}
class TransactionCalculator{

    public static Transection deposit(Account account, double amount){
        double interest = amount * account.interestRate();
        double fee =  100;

        double finalBalance = account.getBalance() + amount+interest-fee;

        return new Transection(amount, 0, interest,fee, finalBalance );



    }

}