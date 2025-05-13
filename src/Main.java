import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingAccount;
import manager.AccountManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.05");
    private static final AccountManager accountManager = new AccountManager();
    public static void main(String[] args) {
        boolean isInUsed = true;
        BankAccount bankAccount;
        String accountNumber;
        double amount;
        while (isInUsed){
            menu();
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    bankAccount = inputAccount();
                    accountManager.addBankAccount(bankAccount);
                    break;
                case 2:
                    bankAccount = inputAccount();
                    accountManager.updateBankAccount(bankAccount);
                    break;
                case 3:
                    accountNumber = inputNumberAccount();
                    accountManager.getBankAccount(accountNumber);
                    break;
                case 4:
                    accountNumber = inputNumberAccount();
                    accountManager.deleteBankAccount(accountNumber);
                    break;
                case 5:
                    accountNumber = inputNumberAccount();
                    amount = inputAmount();
                    accountManager.deposit(accountNumber,amount);
                    break;
                case 6:
                    accountNumber = inputNumberAccount();
                    amount = inputAmount();
                    accountManager.withDraw(accountNumber,amount);
                    break;
                case 7:
                    accountNumber = inputNumberAccount();
                    accountManager.getInterestRate(accountNumber);
                    break;
                case 8:
                    accountNumber = inputNumberAccount();
                    accountManager.getInterestFee(accountNumber);
                    break;
                case 9:
                    isInUsed = false;
                    break;
                default: break;
            }
        }
    }

    private static void menu(){
        System.out.println("Menu");
        System.out.println("1. Thêm tài khoản ngân hàng.");
        System.out.println("2. Sửa thông tin tài khoản.");
        System.out.println("3. Xem thông tin và số dư tài khoản.");
        System.out.println("4. Xóa tài khoản.");
        System.out.println("5. Nạp tiền vào tài khoản.");
        System.out.println("6. Rút tiền.");
        System.out.println("7. Xem lãi suất.");
        System.out.println("8. Xem tiền lãi mỗi năm.");
        System.out.println("9. Thoát");
        System.out.print("Lựa chọn: ");
    }
    private static Double inputAmount(){
        System.out.print("Nhập số tiền: ");
        return scanner.nextDouble();
    }
    private static String inputNumberAccount(){
        System.out.print("Nhập mã tài khoản: ");
        return scanner.next();
    }
    private static BankAccount inputAccount(){
        System.out.println("Chọn loại tài khoản.");
        System.out.println("1. Tài khoản tiết kiệm.");
        System.out.println("2. Tài khoản thanh toán.");
        int choice = scanner.nextInt();
        if(choice == 1){
            SavingAccount savingAccount = new SavingAccount();
            System.out.print("Nhập mã tài khoản: ");
            savingAccount.setAccountNumber(scanner.next());
            scanner.nextLine();
            System.out.print("Nhập tên khách hàng: ");
            savingAccount.setOwnerName(scanner.nextLine());
            savingAccount.setBalance(new BigDecimal(0));
            savingAccount.setInterestRate(INTEREST_RATE);
            return savingAccount;
        }
        CheckingAccount checkingAccount = new CheckingAccount();
        System.out.print("Nhập mã tài khoản: ");
        checkingAccount.setAccountNumber(scanner.next());
        scanner.nextLine();
        System.out.print("Nhập tên khách hàng: ");
        checkingAccount.setOwnerName(scanner.nextLine());
        System.out.print("Nhập hạn mức chi tiêu: ");
        BigDecimal a = new BigDecimal(String.valueOf(scanner.nextBigDecimal()));
        checkingAccount.setOverdraftLimit(a);
        checkingAccount.setBalance(new BigDecimal(0));
        return checkingAccount;
    }
}