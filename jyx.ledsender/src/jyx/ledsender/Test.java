package jyx.ledsender;

//import jdk.nashorn.internal.ir.Block;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import jyx.ledsender.LedUtil.LedContrl;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Structure;

public class Test {

	public static int Handle;

	public static short WM_LEDRETURN = 8000 + 1;
	public static final int Ty_Color_Null = 0x00000000; // δ����
	public static final int Ty_Color_Full = 0x00000003;// ȫ��
	// ��ʾ��ͨѶ��ʽ
	public static final byte DEV_CONNBLOCK = 0; // ����ģʽ
	public static final byte DEV_CONNNOTBLOCK = 1;// ������

	public static int DEV_NONE = -1; // ͨѶ����
	public static int DEV_COM = 0x01;// ����
	public static int DEV_UDP = 0x02;// UDP
	public static int DEV_TCP = 0x03;// TCP

	// ���ڲ�����
	public static final byte SBR_9600 = 0;
	public static final byte SBR_19200 = 1;
	public static final byte SBR_38400 = 2;
	public static final byte SBR_57600 = 3;
	public static final byte SBR_115200 = 4;
	// �߼�������
	public static final byte SCREEN_STYLE_NONE = 0x00;
	public static final byte SCREEN_STYLE_TEXT = 0x01; // ����
	public static final byte SCREEN_STYLE_DIB = 0x02; // ����
	public static final byte SCREEN_STYLE_AVI = 0x03;
	public static final byte SCREEN_STYLE_DCLOCK = 0x04; //
	public static final byte SCREEN_STYLE_ACLOCK = 0x05; //
	public static final short SCREEN_STYLE_TEST = 0xFF;// C++��byteռ16λ��java��byteռ8λ��shortռ16λ
	// ��Ϣ����ֵ
	public static final byte MSG_RXPACK = 1; // ������������
	public static final byte MSG_RXCOMPLETE = 2; // ȫ���������
	public static final byte MSG_TIMEOUT = 3; // ��ʱ
	public static final byte MSG_ERROR = 4; // ����
	public static final byte SOCK_ACCEPT = 1;
	public static final byte SOCK_CONNECT = 2;
	public static final byte SOCK_SEND = 3;

	int packCount;
	int packNo;

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		LedContrl.RDeviceParam.ByReference devparam = new LedUtil.LedContrl.RDeviceParam.ByReference();

		devparam.devType = DEV_UDP;
		devparam.locPort = 1;
		devparam.rmtPort = 2000;
		devparam.rmtHost = 0;
		devparam.groupNo = 0;
		devparam.indexNo = 0;
		devparam.Speed = 7;
		devparam.Handle = Handle;

		NativeLibrary.addSearchPath("LedSender", "D:\\eclipse64\\workspace1");
		NativeLibrary.addSearchPath("LedContrl.class",
				"D:\\eclipse64\\workspace1");
		LedContrl led = (LedContrl) Native.loadLibrary("LedSender",
				LedContrl.class);
		if (led != null) {
			System.out.println("�ɹ���");
		}
		// led.LED_OpenDev(pDevice, block)
		led.LED_Startup();

		// devparam.message = WM_LEDRETURN;
		int dev = led.LED_OpenDev(devparam, false);
		if (dev == -1)
			System.out.println("����ʧ�ܣ�");
		else {
			System.out.println("���ӳɹ���");
			byte[] addrs = InetAddress.getByName("192.168.1.60").getAddress();

			int addr = bytesToInt(addrs);
			led.LED_SetPower(dev, devparam.groupNo, devparam.indexNo, addr,
					devparam.rmtPort, (byte) 1);

		}

	}

	public static int bytesToInt(byte[] bytes) {
		int number = bytes[0] & 0xFF;
		// "|="��λ��ֵ��
		number |= ((bytes[1] << 8) & 0xFF00);
		number |= ((bytes[2] << 16) & 0xFF0000);
		number |= ((bytes[3] << 24) & 0xFF000000);
		return number;
	}

}
