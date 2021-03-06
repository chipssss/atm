package remote;

/**
 * @author: chips
 * @date: 2020-06-03
 * @description: 交易状态码
 **/
public enum TransactionStatusCode {
    SUCCESS, ERROR_WITH_BALANCE("账户余额不足"), ERROR_WITH_LIMIT("超过当天限额"), ERROR_NO_KNOWN("未知错误"),
    ERROR_WITH_CASH_BALANCE("机箱余额不足");

    private String msg;

    TransactionStatusCode() {

    }

    TransactionStatusCode(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
