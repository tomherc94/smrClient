package model.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.cmd.Shell;
import org.hyperic.sigar.cmd.SigarCommandBase;

public class SysInfo extends SigarCommandBase {

	public final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
	public final SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMyyyy-HHmmss");

	public SysInfo(Shell shell) {
		super(shell);
	}

	public SysInfo() {
		super();
	}

	public void output(String[] args) throws SigarException {

		// Data e Hora
		String dateHour = sdf.format(new Date());
		//System.out.println(dateHour);

		// CPU
		org.hyperic.sigar.CpuInfo[] infos = this.sigar.getCpuInfoList();
		org.hyperic.sigar.CpuInfo cpu = infos[0];
		//System.out.println("CPU: " + cpu.getMhz() + " Mhz");
		//org.hyperic.sigar.CpuTimer cpuTimer = new CpuTimer(this.sigar);
		

		// RAM
		Mem mem = this.sigar.getMem();
		//System.out.println("RAM Livre: " + mem.getActualFree() + " bytes");

		// SWAP
		Swap swap = this.sigar.getSwap();
		//System.out.println("SWAP Livre: " + swap.getFree() + " bytes");

		// Disk
		//System.out.println("File Systems........." + Arrays.asList(this.sigar.getFileSystemList()));
		FileSystem[] fslist = this.sigar.getFileSystemList();
		FileSystemUsage usage = null;
		for (FileSystem f : fslist) {
			if (f.getType() == FileSystem.TYPE_LOCAL_DISK) {
				usage = this.sigar.getFileSystemUsage(f.getDirName());
				
				//System.out.println("Disk used percent (" + f.getDirName() + "): " + usage.getUsePercent() * 100);
			}
		}
		//String usagePerc = String.format("%.2f", usage.getUsePercent() * 100);
		try {
			FileWriter arq = new FileWriter("logs\\logCurrent.csv");
			PrintWriter gravarArq = new PrintWriter(arq);

			//gravarArq.println("ipClient,dateHour,cpuMhz,freeRam,freeSwap,diskUsagePerc");
			gravarArq.printf(InetAddress.getLocalHost().getHostAddress());
			gravarArq.print(",");
			gravarArq.printf(dateHour);
			gravarArq.print(",");
			gravarArq.print(cpu.getMhz());
			gravarArq.print(",");
			gravarArq.print(mem.getActualFree());
			gravarArq.print(",");
			gravarArq.print(swap.getFree());
			gravarArq.print(",");
			gravarArq.print(usage.getUsePercent() * 100);
			gravarArq.print(",");
			gravarArq.printf(InetAddress.getLocalHost().getHostAddress());
			gravarArq.print(",");
			gravarArq.printf(System.getProperty("os.name"));
			
			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
