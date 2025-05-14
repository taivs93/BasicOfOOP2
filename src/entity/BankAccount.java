package entity;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import java.math.BigDecimal;

public class BankAccount {
    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;
    public void deposit(double amount) throws InvalidAmountException{
        if(amount > 0){
            this.balance = this.balance.add(new BigDecimal(String.valueOf(amount)));

        }
        else throw new InvalidAmountException("Số tiền giao dịch không được nhỏ hơn hoặc bằng 0!");
    }
    public synchronized void withdraw(double amount) throws InvalidAmountException,InsufficientFundsException{
        BigDecimal amountBigDecimal = new BigDecimal(String.valueOf(amount));
        if (amountBigDecimal.compareTo(BigDecimal.valueOf(0)) <= 0) throw new InvalidAmountException("Số tiền giao dịch không được nhỏ hơn hoặc bằng 0!");
        if(amountBigDecimal.compareTo(this.balance) > 0) throw new InsufficientFundsException("Số dư tài khoản không đủ!");
        this.balance = this.balance.subtract(new BigDecimal(String.valueOf(amount)));
    }
    public synchronized void transferMoney(BankAccount toBankAccount,double amount) throws InvalidAmountException,InsufficientFundsException{
        BigDecimal amountBigDecimal = new BigDecimal(String.valueOf(amount));
        if (amountBigDecimal.compareTo(BigDecimal.valueOf(0)) <= 0) throw new InvalidAmountException("Số tiền giao dịch không được nhỏ hơn hoặc bằng 0!");
        if(amountBigDecimal.compareTo(this.balance) > 0) throw new InsufficientFundsException("Số dư tài khoản không đủ!");
        this.balance = this.balance.subtract(new BigDecimal(String.valueOf(amount)));
        toBankAccount.setBalance(toBankAccount.getBalance().add(amountBigDecimal));
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
