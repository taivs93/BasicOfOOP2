package entity;

import exception.InvalidAmountException;

public enum Selection {
    ADD(1,"Thêm tài khoản"),
    UPDATE(2,"Sửa thông tin tài khoản"),
    GET(3,"Xem thông tin tài khoản"),
    DELETE(4,"Xóa tài khoản"),
    DEPOSIT(5,"Nạp tiền vào tài khoản"),
    WITHDRAW(6,"Rút tiền từ tài khoản"),
    TRANSFER(7,"Thực hiện chuyển tiền"),
    EXIT(8,"Thoát");

    private int value;
    private String description;

    Selection(int value, String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static Selection getSelectionFromValue(int value){
        for (Selection selection : Selection.values()){
            if (selection.getValue() == value) return selection;
        }
        throw new InvalidAmountException("Lựa chọn không hợp lệ.");
    }
}
