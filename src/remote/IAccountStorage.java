package remote;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description:
 **/
public interface IAccountStorage {
    boolean validate(String account);
    boolean validatePassword(String account, String password);
    TransactionStatusCode withdraw(String account, double money);
}
