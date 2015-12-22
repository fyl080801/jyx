package erg.mpis.led102;

import com.sun.jna.Pointer;

public class ResultCBExecute implements LedInterface.ResultCB {

	@Override
	public void procedure(int result, Pointer userdata, Pointer retvalue) {
		// TODO Auto-generated method stub

		if (result == Led102.RESULT_OK) {
	    System.out.println("命令成功！");

		} else if (result == Led102.RESULT_REBOOT) {
        System.out.println("重启成功！");
		}
	}

}