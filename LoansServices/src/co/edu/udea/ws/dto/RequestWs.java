package co.edu.udea.ws.dto;

import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.udea.dto.Device;
import co.edu.udea.dto.User;

@XmlRootElement
public class RequestWs {
	private Date dateRequest;
	private Calendar startTime;
	private Calendar endTime;
	private Device device; /** Referencia a la tabla devices */
	private User researcher; /** Referencia a la tabla users */
	
	public RequestWs(Date dateRequest, Calendar startTime, Calendar endTime, Device device, User researcher) {
		super();
		this.dateRequest = dateRequest;
		this.startTime = startTime;
		this.endTime = endTime;
		this.device = device;
		this.researcher = researcher;
	}

	public RequestWs() {
		super();
	}

	public Date getDateRequest() {
		return dateRequest;
	}
	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}
	public Calendar getStartTime() {
		return startTime;
	}
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	public Calendar getEndTime() {
		return endTime;
	}
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public User getResearcher() {
		return researcher;
	}
	public void setResearcher(User researcher) {
		this.researcher = researcher;
	}
	
	
}
