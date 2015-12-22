package erg.mpis.led102;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sun.jna.Pointer;
import erg.mpis.led102.LedInterface.LedContrl;

/**
 * 10号线2期LED屏控制
 * 
 * @author jiaoyongxin
 *
 */
public class Led102 {
	public static String ledip = "10.0.0.18";
	public static final short RET_PROCESS = 0;// 回掉通知函数调用成功
	public static final short RET_PARAM_ERROR = -1;
	public static final short RET_UNINIT = -2;
	public static final short RET_FAILUE = -3;
	public static final short RET_BUSY = -4;
	public static final short RET_NOMEM = -5;

	// 回调返回的状态值，通信结果
	public static final short RESULT_OK = 0;
	public static final short RESULT_REBOOT = 200;
	public static final short RESULT_UNSUPPORT = 300;
	public static final short RESULT_FAILURE = 301;
	public static final short RESULT_BUSY = 302;
	public static final short RESULT_IGNORE = 303;
	public static final short RESULT_PARAMERROR = 304;
	public static final short RESULT_CONNECTFAIL = 305;
	public static final short RESULT_ILLEGALCMM = 306;
	public static final short RESULT_DATAERROR = 307;
	public static final short RESULT_OTHERERROR = 500;
	// 通信出错
	public static final short RESULT_COMM_ERR = -1;
	// 接收的数据出错
	public static final short RESULT_DATAREVERROR = -2;
	// 超时
	public static final short RESULT_TIMEOUT = -3;
	// 停止
	public static final short RESULT_STOP = -3;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		LedContrl led = LedInterface.getLedContrl();
		if (led != null) {
			System.out.println("动态链接库加载成功！");
		}

		int timeout = 6000;
		led.InitCommEnv(timeout);
		Thread.sleep(1000);
		byte[] cardip = InetAddress.getByName(ledip).getAddress();
		// char[] cardip = new String("addr").toCharArray();
		led.SelectNetChanel(cardip);
		LedInterface.ResultCB cb = new ResultCBExecute();
		Pointer userdata = null;

		led.SetDefaultLight(1, cb, userdata);
		led.SetDefaultLight(22, cb, userdata);
		led.PowerOffLed(cb, userdata);
		led.PowerOnLed(cb, userdata);
		led.ReleaseCommEnv();
	}

}
