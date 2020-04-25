package model.entities;

public class Log {

	private Integer id;
	private String ipClient;
	private String dateHour;
	private Long freeRam;
	private Long freeSwap;
	private Double cpu;
	private Double diskUsage;
	
	public Log() {
		
	}

	public Log(Integer id, String ipClient, String dateHour, Long freeRam, Long freeSwap, Double cpu,
			Double diskUsage) {
		super();
		this.id = id;
		this.ipClient = ipClient;
		this.dateHour = dateHour;
		this.freeRam = freeRam;
		this.freeSwap = freeSwap;
		this.cpu = cpu;
		this.diskUsage = diskUsage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIpClient() {
		return ipClient;
	}

	public void setIpClient(String ipClient) {
		this.ipClient = ipClient;
	}

	public String getDateHour() {
		return dateHour;
	}

	public void setDateHour(String dateHour) {
		this.dateHour = dateHour;
	}

	public Long getFreeRam() {
		return freeRam;
	}

	public void setFreeRam(Long freeRam) {
		this.freeRam = freeRam;
	}

	public Long getFreeSwap() {
		return freeSwap;
	}

	public void setFreeSwap(Long freeSwap) {
		this.freeSwap = freeSwap;
	}

	public Double getCpu() {
		return cpu;
	}

	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}

	public Double getDiskUsage() {
		return diskUsage;
	}

	public void setDiskUsage(Double diskUsage) {
		this.diskUsage = diskUsage;
	}
	
}
