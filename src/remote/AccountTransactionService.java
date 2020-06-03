package remote;

/**
 * 模拟远程账户交易类
 * ATM实际为客户端系统，需要远程账户交易系统作为后台。
 * 本程序简化直接使用本地类，但是是非常不合理的。
 * 服务类设计可参考NextGenPos将账户对象加载内存方式，也可考虑使用数据库。
 * 
 * 更形象各组可以单独设计远程服务程序。
 * ATM和远程服务程序之间通讯，可采用socket、web service soap或restful Api接口
 * 推荐各组将账户交易系统单独设计成程序。具体实现方式各组自行设计完成。
 */
public class AccountTransactionService {
	private IAccountStorage storage;
	private static int LIMIT_DAILY = 2000;

	public AccountTransactionService() {
		storage = new LocalAccountStorage(LIMIT_DAILY);
	}

	/**
	 * 判断用户是否存在
	 * @param account
	 * @return
	 */
	public boolean validateAccount(String account) {
		return storage.validate(account);
	}

	/**
	 * 远程账户验证服务
	 */
	public boolean validatePassword(String account, String password){ //返回值及参数各组自行设计
		return storage.validatePassword(account, password);
	}

	/**
	 * 远程账户交易服务
	 */
	public TransactionStatusCode withDraw(String account, int money){ //返回值及参数各组自行设计
		return storage.withdraw(account, money);
	}
}
