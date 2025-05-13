package entity;

import java.math.BigDecimal;

public class SavingAccount extends BankAccount{
    private BigDecimal interestRate;

    public BigDecimal getInterestFeePerYear(){
        return this.getBalance().multiply(this.interestRate);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "accountNumber='" + this.getAccountNumber() + '\'' +
                ", ownerName='" + this.getOwnerName() + '\'' +
                ", balance=" + this.getBalance() + '\'' +
                ", interestRate=" + this.interestRate +
                '}';
    }
}
