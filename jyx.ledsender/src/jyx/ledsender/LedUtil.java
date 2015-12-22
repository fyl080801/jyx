package jyx.ledsender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Structure;

public class LedUtil {

	public interface LedContrl extends Library {

		public static class RDeviceParam extends Structure {

			public static class ByReference extends RDeviceParam implements
					Structure.ByReference {
			}

			public int devType; // 通讯类型
			public byte groupNo; // 设备组号
			public byte indexNo;// 设备屏号
			public int Speed; // 串口速率
			public int locPort;// 本地端口
			public int rmtPort;// 设备端口
			public int rmtHost;// 设备ip
			public int Notify;
			public int Window;
			public int message;
			public int Handle;

			@Override
			protected List getFieldOrder() {
				return Arrays.asList(new String[] { "devType", "groupNo",
						"indexNo", "Speed", "locPort", "rmtPort", "rmtHost",
						"Notify", "Window", "message", "Handle" });
			}
		}

		public static class RLogicLed extends Structure {

			public static class ByReference extends RLogicLed implements
					Structure.ByReference {
			}

			public byte LocigPagNo; // 所属逻辑页号
			public byte LedNo; // 逻辑屏号
			public byte Info; // 属性字节
			public byte LogicType; // 逻辑屏类型
			public short PosLeft; // 逻辑屏X轴始点坐标
			public short PosTop; // 逻辑屏Y轴始点坐标
			public short PosRight; // 逻辑屏X轴终点坐标
			public short PosBottom; // 逻辑屏Y轴终点坐标
			public byte MoveInMode; // 引入方式
			public byte MoveInSpeed; // 引入速度
			public byte MoveOutMode; // 引出方式
			public byte MoveOutSpeed; // 引出速度
			public int StayTime; // 停留时间
			public byte MoveSpace; // 间隔距离
			public short ObjCount; // 所包含的对象个数
			public int ObjSize; // 所包含对象的总内容长度
			public byte Hold1; // 保留
			public byte Hold2; // 保留
			public byte Hold3; // 保留

			@Override
			protected List getFieldOrder() {
				// TODO Auto-generated method stub
				return null;
			}

		}

		public static class RLogicPage extends Structure {

			public static class ByReference extends RLogicPage implements
					Structure.ByReference {
			}

			public short PagNo; // 页号
			public byte Info;
			public short ViewTime; // 页时间
			public byte LoopCount;
			public short LedCounts;
			public byte Hold1;
			public int SizeOf;
			public short Hold2;

			@Override
			protected List getFieldOrder() {
				// TODO Auto-generated method stub
				return null;
			}

		}

		public void LED_Startup();

		public void LED_ClearUp();

		public int LED_OpenDev(RDeviceParam.ByReference pDevice, boolean block);

		public int LED_CloseDev(int tag);

		public boolean LED_MakeRoot(byte screenType);

		public boolean LED_AddLogic(RLogicPage.ByReference logicPage);

		public boolean LED_AddLogicLed(byte logicPagNo,
				RLogicLed.ByReference logicLed);// 添加逻辑屏

		public boolean LED_AddWidnows(int logicPagNo, int logicLedNo,
				byte MoveInMode, byte MoveInSpeed, byte MoveOutMode,
				byte MoveOutSpeed, byte ViewMode, int ViewTime, int WinWidth,
				int WinHeight, int winhdc);

		public int LED_SendToScreen(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);// 送往设备

		public int LED_SetPower(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort, byte Power);

		public int LED_AdJust(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);// 校时

		public int LED_SetBright(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort, byte BrightMode, byte BrightValue);

		public int LED_Reset(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);
	}

}
