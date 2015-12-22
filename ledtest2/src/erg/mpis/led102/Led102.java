package erg.mpis.led102;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sun.jna.Pointer;
import erg.mpis.led102.LedInterface.LedContrl;

/**
 * 10����2��LED������
 * 
 * @author jiaoyongxin
 *
 */
public class Led102 {
	public static String ledip = "10.0.0.18";
	public static final short RET_PROCESS = 0;// �ص�֪ͨ�������óɹ�
	public static final short RET_PARAM_ERROR = -1;
	public static final short RET_UNINIT = -2;
	public static final short RET_FAILUE = -3;
	public static final short RET_BUSY = -4;
	public static final short RET_NOMEM = -5;

	// �ص����ص�״ֵ̬��ͨ�Ž��
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
	// ͨ�ų���
	public static final short RESULT_COMM_ERR = -1;
	// ���յ����ݳ���
	public static final short RESULT_DATAREVERROR = -2;
	// ��ʱ
	public static final short RESULT_TIMEOUT = -3;
	// ֹͣ
	public static final short RESULT_STOP = -3;

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		LedContrl led = LedInterface.getLedContrl();
		if (led != null) {
			System.out.println("��̬���ӿ���سɹ���");
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
