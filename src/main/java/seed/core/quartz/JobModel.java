package seed.core.quartz;

public class JobModel implements java.io.Serializable{

	private static final long serialVersionUID = 4063159070549404182L;

	private String name;
	
	private String job;
	
	private String corn;
	
	private boolean enable;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCorn() {
		return corn;
	}

	public void setCorn(String corn) {
		this.corn = corn;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

	
	
}
