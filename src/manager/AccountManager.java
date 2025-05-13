package manager;

import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountManager {
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public void addBankAccount(BankAccount bankAccount){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(bankAccount.getAccountNumber());
        if (existedBankAccount.isEmpty()) {
            if (bankAccount instanceof CheckingAccount){
                bankAccounts.add(bankAccount);
                System.out.println("Thêm tài khoản thanh toán thành công.");
            }
            else if (bankAccount instanceof SavingAccount) {
                bankAccounts.add(bankAccount);
                System.out.println("Thêm tài khoản tiết kiệm thành công.");
            }
        }
        else System.out.println("Mã số tài khoản đã tồn tại.");
    }

    public void updateBankAccount(BankAccount bankAccount){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(bankAccount.getAccountNumber());
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản");
            return;
        }
        if (bankAccount instanceof CheckingAccount && existedBankAccount.get() instanceof CheckingAccount) {
            existedBankAccount.get().setOwnerName(bankAccount.getOwnerName());
            existedBankAccount.get().setBalance(bankAccount.getBalance());
            ((CheckingAccount) existedBankAccount.get()).setOverdraftLimit(((CheckingAccount) bankAccount).getOverdraftLimit());
            System.out.println("Cập nhật thông tin tài khoản thành công.");
        }
        else if(bankAccount instanceof SavingAccount && existedBankAccount.get() instanceof SavingAccount){
            existedBankAccount.get().setOwnerName(bankAccount.getOwnerName());
            existedBankAccount.get().setBalance(bankAccount.getBalance());
            ((SavingAccount) existedBankAccount.get()).setInterestRate(((SavingAccount) bankAccount).getInterestRate());
            System.out.println("Cập nhật thông tin tài khoản thành công.");
        }
        else {
            System.out.println("Cập nhật thông tin tài khoản không thành công.");
        }
    }

    public void deleteBankAccount(String accountNumber){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        this.bankAccounts.remove(existedBankAccount.get());
        System.out.println("Xóa tài khoản thành công.");
    }

    public void getBankAccount(String accountNumber){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        System.out.println(existedBankAccount.get());
    }

    public void getInterestRate(String accountNumber){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        if(existedBankAccount.get() instanceof CheckingAccount) {
            System.out.println("Không thể xem lãi suất với loại tài khoản.");
        }
        else if (existedBankAccount.get() instanceof SavingAccount){
            System.out.println("Lãi suất tiết kiệm: " + ((SavingAccount) existedBankAccount.get()).getInterestRate());
        }
    }

    public void getInterestFee(String accountNumber){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        if(existedBankAccount.get() instanceof CheckingAccount) {
            System.out.println("Không thể xem tiền lãi với loại tài khoản.");
        }
        else if (existedBankAccount.get() instanceof SavingAccount){
            System.out.println("Tiền lãi sau 1 năm: " + ((SavingAccount) existedBankAccount.get()).getInterestFeePerYear());
        }
    }

    public void deposit(String accountNumber, double amount){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        existedBankAccount.get().deposit(amount);
    }

    public void withDraw(String accountNumber, double amount){
        Optional<BankAccount> existedBankAccount = this.findBankAccountByNumber(accountNumber);
        if(existedBankAccount.isEmpty()){
            System.out.println("Không tìm thấy mã số tài khoản.");
            return;
        }
        existedBankAccount.get().withdraw(amount);
    }
    private Optional<BankAccount> findBankAccountByNumber(String accountNumber){
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getAccountNumber().equals(accountNumber)).findFirst();
    }
}
