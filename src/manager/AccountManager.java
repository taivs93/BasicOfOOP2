package manager;

import entity.BankAccount;
import entity.CheckingAccount;
import entity.SavingAccount;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class AccountManager {
    private static List<BankAccount> bankAccounts = new ArrayList<>();
    private static Logger logger = Logger.getLogger(AccountManager.class.getName());
    static {
        try {
            FileHandler fileHandler = new FileHandler("log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException ignored) {

        }
    }
    public void addBankAccount(BankAccount bankAccount){
        try {
            logger.info("Thực hiện thêm tài khoản.");
            BankAccount existedAccount = findBankAccountByNumber(bankAccount.getAccountNumber());
            logger.warning("Mã số tài khoản đã tồn tại.");
        } catch (AccountNotFoundException accountNotFoundException){
            bankAccounts.add(bankAccount);
            if (bankAccount instanceof CheckingAccount) logger.info("Thêm tài khoản thanh toán thành công.");
            else if(bankAccount instanceof SavingAccount) logger.info("Thêm tài khoản tiết kiệm thành công.");
        } finally {
            logger.info("Thêm tài khoản kết thúc");
        }
    }

    public void updateBankAccount(BankAccount bankAccount){
        try {
            logger.info("Thực hiện cập nhật thông tin tài khoản.");
            BankAccount existedAccount = findBankAccountByNumber(bankAccount.getAccountNumber());
            if (bankAccount instanceof CheckingAccount && existedAccount instanceof CheckingAccount) {
                existedAccount.setOwnerName(bankAccount.getOwnerName());
                existedAccount.setBalance(bankAccount.getBalance());
                ((CheckingAccount) existedAccount).setOverdraftLimit(((CheckingAccount) bankAccount).getOverdraftLimit());
                logger.info("Cập nhật thông tin tài khoản thành công.");
            }
            else if(bankAccount instanceof SavingAccount && existedAccount instanceof SavingAccount){
                existedAccount.setOwnerName(bankAccount.getOwnerName());
                existedAccount.setBalance(bankAccount.getBalance());
                ((SavingAccount) existedAccount).setInterestRate(((SavingAccount) bankAccount).getInterestRate());
                logger.info("Cập nhật thông tin tài khoản thành công");
            }
        } catch (AccountNotFoundException accountNotFoundException){
            logger.warning(accountNotFoundException.getMessage());
            throw accountNotFoundException;
        } finally {
            logger.info("Cập nhật thông tin tài khoản kết thúc.");
        }
    }

    public void deleteBankAccount(String accountNumber) throws AccountNotFoundException{
        try {
            logger.info("Thực hiện xóa tài khoản");
            BankAccount existedAccount = findBankAccountByNumber(accountNumber);
            bankAccounts.remove(existedAccount);
            logger.info("Xóa tài khoản thành công.");
        } catch (AccountNotFoundException accountNotFoundException){
            logger.warning(accountNotFoundException.getMessage());
            throw accountNotFoundException;
        } finally {
            logger.info("Kết thúc xóa tài khoản");
        }

    }

    public void getBankAccount(String accountNumber) throws AccountNotFoundException{
        try {
            logger.info("Thực hiện xem thông tin tài khoản");
            BankAccount existedAccount = findBankAccountByNumber(accountNumber);
            System.out.println(existedAccount);
            logger.info("Xem thông tin tài khoản thành công");
        } catch (AccountNotFoundException accountNotFoundException){
            logger.warning(accountNotFoundException.getMessage());
            throw accountNotFoundException;
        } finally {
            logger.info("Kết thúc xem tài khoản");
        }
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException{
        try{
            BankAccount existedAccount = findBankAccountByNumber(accountNumber);
            logger.info("Thực hiện nạp tiền vào tài khoản.");
            existedAccount.deposit(amount);
            logger.info("Nạp tiền vào tai khoản thành công");
        } catch (InvalidAmountException | InsufficientFundsException exception){
            logger.warning(exception.getMessage());
        }
        finally {
            logger.info("Giao dịch kết thúc");
        }
    }

    public void withDraw(String accountNumber, double amount) throws AccountNotFoundException{
        try{
            BankAccount existedAccount = findBankAccountByNumber(accountNumber);
            logger.info("Thực hiện giao dịch rút tiền.");
            existedAccount.withdraw(amount);
            logger.info("Rút tiền thành công.");
        }
        catch (AccountNotFoundException accountNotFoundException){
            logger.warning(accountNotFoundException.getMessage());
            throw accountNotFoundException;
        }
        catch (InvalidAmountException | InsufficientFundsException exception){
            logger.warning(exception.getMessage());
        }
        finally {
            logger.info("Giao dịch kết thúc.");
        }
    }
    public void transferMoney(String accountNumberFrom, String accountNumberTo, double amount) throws AccountNotFoundException{
        try{
            BankAccount existedAccountFrom = findBankAccountByNumber(accountNumberFrom);
            BankAccount existedAccountTo = findBankAccountByNumber(accountNumberTo);
            logger.info("Thực hiện giao dịch chuyển tiền");
            existedAccountFrom.transferMoney(existedAccountTo,amount);
            logger.info("Chuyển tiền thành công.");
        }
        catch (AccountNotFoundException accountNotFoundException){
            logger.warning(accountNotFoundException.getMessage());
            throw accountNotFoundException;
        }
        catch (InvalidAmountException | InsufficientFundsException exception){
            logger.warning(exception.getMessage());
        }
        finally {
            logger.info("Giao dịch kết thúc.");
        }
    }
    private static BankAccount findBankAccountByNumber(String accountNumber) throws  AccountNotFoundException{
        return bankAccounts.stream().filter(bankAccount -> bankAccount.getAccountNumber()
                .equals(accountNumber)).findFirst().orElseThrow(() -> new AccountNotFoundException("Không tìm thấy mã số tài khoản."));
    }
}
