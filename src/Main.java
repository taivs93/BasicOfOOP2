import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingAccount;
import entity.Selection;
import manager.AccountManager;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.05");
    private static final AccountManager accountManager = new AccountManager();
    public static void main(String[] args) {
        boolean isInUsed = true;
        BankAccount bankAccount;
        String accountNumber;
        String accountNumberFrom;
        String accountNumberTo;
        double amount;
        while (isInUsed){
            menu();
            int choice = scanner.nextInt();
            Selection selection = Selection.getSelectionFromValue(choice);
            switch (selection){
                case ADD:
                    bankAccount = inputAccount();
                    accountManager.addBankAccount(bankAccount);
                    break;
                case UPDATE:
                    bankAccount = inputAccount();
                    accountManager.updateBankAccount(bankAccount);
                    break;
                case GET:
                    accountNumber = inputNumberAccount();
                    accountManager.getBankAccount(accountNumber);
                    break;
                case DELETE:
                    accountNumber = inputNumberAccount();
                    accountManager.deleteBankAccount(accountNumber);
                    break;
                case DEPOSIT:
                    accountNumber = inputNumberAccount();
                    amount = inputAmount();
                    accountManager.deposit(accountNumber,amount);
                    break;
                case WITHDRAW:
                    accountNumber = inputNumberAccount();
                    amount = inputAmount();
                    accountManager.withDraw(accountNumber,amount);
                    break;
                case TRANSFER:
                    accountNumberFrom = inputNumberAccount();
                    accountNumberTo = inputNumberAccount();
                    amount = inputAmount();
                    accountManager.transferMoney(accountNumberFrom,accountNumberTo,amount);
                    break;
                case EXIT:
                    isInUsed = false;
                    break;
                default: break;
            }
        }
    }

    private static void menu(){
        System.out.println("Menu");
        for (Selection selection : Selection.values()){
            System.out.println(selection.getValue() + ". "+selection.getDescription());
        }
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