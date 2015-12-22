package erg.mpis.led102;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class LedInterface {
	
	public static LedContrl getLedContrl() {
		// NativeLibrary.addSearchPath("LedContrl", "\\");
		// NativeLibrary.addSearchPath("LedContrl.class",
		// "D:\\eclipse64\\workspace1");
		// NativeLibrary.addSearchPath("", "/");
		return (LedContrl) Native.loadLibrary("interface.dll", LedContrl.class);
	}

	public interface ResultCB extends StdCallCallback {

		public void procedure(int result, Pointer userdata, Pointer retvalue);
	}

	public interface LedContrl extends Library {
		// PowerOnLed
		public int InitCommEnv(int timeout);

		public int SelectNetChanel(byte[] cardip);

		public int PowerOnLed(ResultCB cb, Pointer userdata);

		public int PowerOffLed(ResultCB cb, Pointer userdata);

		// public int SetLightLevel(PBrightLevelinfo brightarray,ResultCB
		// cb,Pointer userdata);//LED 亮度设置

		public int SetDefaultLight(int level, ResultCB cb, Pointer userdata);// LED
																				// 默认亮度

		public int SetTmpLightLevel(int levle, ResultCB cb, Pointer userdat);// 临时调节LED亮度

		public int ReleaseCommEnv();
	}

}
