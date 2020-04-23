package model.entities;

public class Client {

	private Integer id;
	private String hostname;
	private String ip;
	private Log logCurrent;

	
	public Client() {
		
	}

	public Client(Integer id, String hostname, String ip, Log logCurrent) {
		super();
		this.id = id;
		this.hostname = hostname;
		this.ip = ip;
		this.logCurrent = logCurrent;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Log getLogCurrent() {
		return logCurrent;
	}

	public void setLogCurrent(Log logCurrent) {
		this.logCurrent = logCurrent;
	}

}
