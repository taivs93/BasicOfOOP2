package entity;

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
    public void withdraw(double amount) {
        BigDecimal amountBigDecimal = new BigDecimal(String.valueOf(amount));
        if(amount > 0 && amountBigDecimal.compareTo(this.getBalance()) <= 0
                && amountBigDecimal.compareTo(this.overdraftLimit) <= 0){
            this.setBalance(this.getBalance().subtract(new BigDecimal(String.valueOf(amount))));
            System.out.println("Thực hiện rút tiền thành công.");
            System.out.println("Số dư: "+ this.getBalance());
        }
        else System.out.println("Không thể thực hiện rút tiền");
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
