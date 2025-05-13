package entity;

import java.math.BigDecimal;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;
    public void deposit(double amount){
        if(amount > 0){
            this.balance = this.balance.add(new BigDecimal(String.valueOf(amount)));
            System.out.println("Nạp tiền thành công.");
        }
        else System.out.println("Không thể nạp tiền.");
    }
    public synchronized void withdraw(double amount){
        BigDecimal amountBigDecimal = new BigDecimal(String.valueOf(amount));
        if(amount > 0 && amountBigDecimal.compareTo(this.balance) <= 0){
            this.balance = this.balance.subtract(new BigDecimal(String.valueOf(amount)));
            System.out.println("Rút thành công số tiền: " + amount);
            System.out.println("Số dư tài khoản: " + this.balance);
        }
        else System.out.println("Không thể thực hiện rút tiền.");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
