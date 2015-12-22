package erg.mpis.led102;

import java.util.Arrays;
import java.util.List;

//import erg.mpis.led102.LedInterface.LedContrl.PBrightLevelinfo;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.OaIdl.VARDESC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary.StdCallCallback;

public class LedInterface {

	public static LedContrl getLedContrl() {
		NativeLibrary.addSearchPath("interface", "D:\\eclipse64\\workspace1");
	//	NativeLibrary.addSearchPath("LedContrl.class",
	//			"D:\\eclipse64\\workspace1");
		LedContrl led = (LedContrl) Native.loadLibrary("interface",
				LedContrl.class);
		return led;
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
		
	//	public int SetLightLevel(PBrightLevelinfo brightarray,ResultCB cb,Pointer userdata);//LED 亮度设置
		
		public int SetDefaultLight(int level,ResultCB cb, Pointer userdata);//LED 默认亮度
		
		public int SetTmpLightLevel(int levle,ResultCB cb, Pointer userdat);//临时调节LED亮度

		public int ReleaseCommEnv();
	}

	
}
