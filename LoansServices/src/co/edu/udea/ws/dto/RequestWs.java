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
	
	/**
	 * Constructor de request con parametros
	 * @param dateRequest
	 * @param startTime
	 * @param endTime
	 * @param device
	 * @param researcher
	 */
	public RequestWs(Date dateRequest, Calendar startTime, Calendar endTime, Device device, User researcher) {
		super();
		this.dateRequest = dateRequest;
		this.startTime = startTime;
		this.endTime = endTime;
		this.device = device;
		this.researcher = researcher;
	}
	
	/**
	 * Constructor de request sin parametros
	 */
	public RequestWs() {
		super();
	}

	/**
	 * Obtiene la fecha de solicitud de un Request
	 * @return dateRequest
	 */
	public Date getDateRequest() {
		return dateRequest;
	}
	/**
	 * Modifica la fecha de solicitud de un Request
	 */
	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}
	/**
	 * Obtiene el tiempo de inicio de un Request
	 * @return startTime
	 */
	public Calendar getStartTime() {
		return startTime;
	}
	/**
	 * Modifica el tiempo de inicio de un Request
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
	/**
	 * Obtiene el tiempo de finalizaci�n de un Request
	 * @return endTime
	 */
	public Calendar getEndTime() {
		return endTime;
	}
	/**
	 * Modifica el tiempo de finalizaci�n de un Request
	 */
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	/**
	 * Obtiene el dispositivo de un Request
	 * @return device
	 */
	public Device getDevice() {
		return device;
	}
	/**
	 * Modifica el dispositivo de un Request
	 */
	public void setDevice(Device device) {
		this.device = device;
	}
	/**
	 * Obtiene el investigador de un Request
	 * @return reseracher
	 */
	public User getResearcher() {
		return researcher;
	}
	/**
	 * Modifica el investigador de un Request
	 */
	public void setResearcher(User researcher) {
		this.researcher = researcher;
	}
	
	
}
