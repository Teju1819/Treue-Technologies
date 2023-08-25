package appointment;

import java.time.LocalDateTime;

public class Appointment
{
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String serviceProvider;
    private User user;

  //  User user=new User(username,email);
   

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, String serviceProvider,User user) {
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.serviceProvider = serviceProvider;
		 this.user=user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

	@Override
	public String toString() {
		return "Appointment [startTime=" + startTime + ", endTime=" + endTime + ", serviceProvider=" + serviceProvider
				+ ", user=" + user + "]";
	}



}
