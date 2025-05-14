package entity;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;

import java.math.BigDecimal;

public class CheckingAccount extends BankAccount{
    private BigDecimal overdraftLimit;

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amount) {
        BigDecimal amountBigDecimal = new BigDecimal(String.valueOf(amount));
        if (amountBigDecimal.compareTo(BigDecimal.valueOf(0)) <= 0) throw new InsufficientFundsException("Số tiền giao dịch không được nhỏ hơn hoặc bằng 0!");
        if(amountBigDecimal.compareTo(this.getBalance()) > 0) throw new InvalidAmountException("Số dư tài khoản không đủ!");
        if(amountBigDecimal.compareTo(this.overdraftLimit) > 0) {
            System.out.println("Số tiền vượt hạn mức chi tiêu.");
        }
        this.setBalance(this.getBalance().subtract(new BigDecimal(String.valueOf(amount))));
        System.out.println("Rút thành công số tiền: " + amount);
        System.out.println("Số dư tài khoản: " + this.getBalance());
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "accountNumber='" + this.getAccountNumber() + '\'' +
                ", ownerName='" + this.getOwnerName() + '\'' +
                ", balance=" + this.getBalance() + '\'' +
                ", interestRate=" + overdraftLimit +
                '}';
    }
}
