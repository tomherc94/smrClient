package aplication;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.cmd.SigarCommandBase;

public class SysInfo extends SigarCommandBase {

	public SysInfo(Shell shell) {
		super(shell);
	}

	public SysInfo() {
		super();
	}

	public String getUsageShort() {
		return "Display system information";
	}

	public void output(String[] args) throws SigarException {
		/*
		 * org.hyperic.sigar.CpuInfo[] infos = this.sigar.getCpuInfoList(); //CpuPerc[]
		 * cpus = this.sigar.getCpuPercList(); org.hyperic.sigar.CpuInfo info =
		 * infos[0];
		 * 
		 * println("Vendor........." + info.getVendor()); println("Model.........." +
		 * info.getModel()); println("Mhz............" + info.getMhz());
		 * println("Total CPUs....." + info.getTotalCores());
		 */

		Mem mem = this.sigar.getMem();
		//Swap swap = this.sigar.getSwap();

		System.out.println("Livre: " + mem.getActualFree());

	}

	/*
	 * public static void main(String[] args) throws Exception { new
	 * SysInfo().processCommand(args); }
	 */
}
