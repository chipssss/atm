package remote;

import entity.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description: 模拟本地存储
 **/
public class LocalAccountStorage implements IAccountStorage {

    private Map<String, Account> storage = new HashMap<>();

    /**
     * 账户当天限额
     */
    private int limitToday;

    public LocalAccountStorage(int limitToday) {
        this.limitToday = limitToday;
        storage.put("123456", new Account("123456", "123456", 3000));
        storage.put("12345", new Account("12345", "12345", 2000));
    }

    @Override
    public boolean validate(String account) {
        return storage.containsKey(account);
    }

    @Override
    public boolean validatePassword(String account, String password) {
        Account cur = storage.get(account);
        if (cur == null)
            return false;
        return cur.validate(account);
    }

    @Override
    public TransactionStatusCode withdraw(String account, int money) {
        Account currentUser = storage.get(account);
        if (currentUser == null) {
            return TransactionStatusCode.ERROR_NO_KNOWN;
        }
        // 是否超过当天限额
        if (currentUser.getTotalDaily() + money > limitToday) {
            return TransactionStatusCode.ERROR_WITH_LIMIT;
        }
        boolean isSuccess = currentUser.withdraw(money);
        return isSuccess? TransactionStatusCode.SUCCESS: TransactionStatusCode.ERROR_WITH_BALANCE;
    }

}
