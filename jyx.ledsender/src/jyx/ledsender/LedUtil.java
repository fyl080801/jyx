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

			public int devType; // ͨѶ����
			public byte groupNo; // �豸���
			public byte indexNo;// �豸����
			public int Speed; // ��������
			public int locPort;// ���ض˿�
			public int rmtPort;// �豸�˿�
			public int rmtHost;// �豸ip
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

			public byte LocigPagNo; // �����߼�ҳ��
			public byte LedNo; // �߼�����
			public byte Info; // �����ֽ�
			public byte LogicType; // �߼�������
			public short PosLeft; // �߼���X��ʼ������
			public short PosTop; // �߼���Y��ʼ������
			public short PosRight; // �߼���X���յ�����
			public short PosBottom; // �߼���Y���յ�����
			public byte MoveInMode; // ���뷽ʽ
			public byte MoveInSpeed; // �����ٶ�
			public byte MoveOutMode; // ������ʽ
			public byte MoveOutSpeed; // �����ٶ�
			public int StayTime; // ͣ��ʱ��
			public byte MoveSpace; // �������
			public short ObjCount; // �������Ķ������
			public int ObjSize; // ����������������ݳ���
			public byte Hold1; // ����
			public byte Hold2; // ����
			public byte Hold3; // ����

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

			public short PagNo; // ҳ��
			public byte Info;
			public short ViewTime; // ҳʱ��
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
				RLogicLed.ByReference logicLed);// ����߼���

		public boolean LED_AddWidnows(int logicPagNo, int logicLedNo,
				byte MoveInMode, byte MoveInSpeed, byte MoveOutMode,
				byte MoveOutSpeed, byte ViewMode, int ViewTime, int WinWidth,
				int WinHeight, int winhdc);

		public int LED_SendToScreen(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);// �����豸

		public int LED_SetPower(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort, byte Power);

		public int LED_AdJust(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);// Уʱ

		public int LED_SetBright(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort, byte BrightMode, byte BrightValue);

		public int LED_Reset(int dev, byte devGroupNo, byte devIndexNo,
				int rmHost, int rmPort);
	}

}
