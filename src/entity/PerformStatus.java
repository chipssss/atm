package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: chips
 * @date: 2020-06-06
 * @description: ATM运行中间状态记录
 **/
public class PerformStatus {
    private String account;
    private String receipt;
    /**
     * 交易成功涉及金额
     */
    private double transactionMoney;

    public PerformStatus() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(double quantity, Operation operation) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // 金额保留两位小数
        this.receipt = String.format("账户%s于%s %s ¥%.2f", account, format.format(new Date()), operation.getName(), quantity);
    }

    public double getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(double transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

    /**
     * 退卡时清除掉状态
     */
    public void reset() {
        account = null;
        receipt = null;
        transactionMoney = 0;
    }
}
