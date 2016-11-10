package co.edu.udea.ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * DTO para los servicios de devices
 * @author Oscar Zapata, Jonnatan Ríos, Diego Álvarez
 *
 */
@XmlRootElement
public class DeviceWs {
	private Long id;
	private String name;
	private int number;
	private boolean state;
	
	/**
	 * Constructor de devices con parametros
	 * @param id
	 * @param name
	 * @param number
	 * @param state
	 */
	public DeviceWs(Long id, String name, int number, boolean state) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.state = state;
	}	
	
	/**
	 * Constructor de devices sin parametros
	 */
	public DeviceWs() {
		super();
	}

	/**
	 * Obtiene el id de un Device
	 * @return id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Modifica el id de un Device
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * Obtiene el nombre de un Device
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Modifica el nombre de un Device
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Obtiene el numero de un Device
	 * @return number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * Modifica el numero de un Device
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Obtiene el estado de un Device
	 * @return state
	 */
	public boolean getState() {
		return state;
	}
	/**
	 * Modifica el estado de un Device
	 */
	public void setState(boolean state) {
		this.state = state;
	}
	
}
