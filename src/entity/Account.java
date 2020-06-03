package entity;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description:
 **/
public class Account {
    private String account;
    private String password;
    private int balance;
    /**
     * 当天累计
     */
    private int totalDaily;

    public Account(String account, String password, int balance) {
        this.account = account;
        this.password = password;
        this.balance = balance;
    }

    public boolean validate(String password) {
        return this.password != null && this.password.equals(password);
    }

    /**
     *
     * @param money
     * @return true 提款成功， false，失败
     */
    public boolean withdraw(int money) {
        boolean success = balance - money >= 0;
        if (success) {
            balance -= money;
            totalDaily += money;
        }
        return success;
    }

    public int getTotalDaily() {
        return totalDaily;
    }
}
