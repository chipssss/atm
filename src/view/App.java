package view;

/**
 * 程序启动类
 *
 */
public class App {
	public static void main(String[] args) {
		AtmFrame frame = new AtmFrame();
		//仅供测试查看登录面板GUI效果
		//frame.refreshDisplayPanel(frame.getLoginPanel());
		
		//仅供测试查看取款面板GUI效果
		//frame.refreshDisplayPanel(frame.getWithdrawlPanel());
		
		//仅供测试查看打印凭证面板GUI效果
		//frame.refreshDisplayPanel(frame.getPrintPanel());
		
		//仅供测试交易服务菜面板GUI效果
		//frame.refreshDisplayPanel(frame.getServicePanel());
	}
}