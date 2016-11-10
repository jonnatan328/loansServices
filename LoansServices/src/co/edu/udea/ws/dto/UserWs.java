package co.edu.udea.ws.dto;

import javax.xml.bind.annotation.XmlRootElement;

import co.edu.udea.dto.Role;

/**
 * DTO para los servicios de users
 * @author Oscar Zapata, Jonnatan R�os, Diego �lvarez
 *
 */
@XmlRootElement
public class UserWs {
	private String username;
	private String names;
	private String lastnames;
	private String password;
	private Role role; /** Referencia a la tabla roles */
	
	/**
	 * Constructor de devices con parametros
	 * @param username
	 * @param names
	 * @param lastnames
	 * @param password
	 * @param role
	 */
	public UserWs(String username, String names, String lastnames, String password, Role role) {
		super();
		this.username = username;
		this.names = names;
		this.lastnames = lastnames;
		this.password = password;
		this.role = role;
	}
	
	/**
	 * Constructor de user sin parametros
	 */
	public UserWs() {
		super();
	}

	/**
	 * Obtiene el username de un User
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Modifica el username de un User
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Obtiene los nombres de un User
	 * @return names
	 */
	public String getNames() {
		return names;
	}
	/**
	 * Modifica los nombres de un User
	 */
	public void setNames(String names) {
		this.names = names;
	}
	/**
	 * Obtiene los apellidos de un User
	 * @return lastnames
	 */
	public String getLastnames() {
		return lastnames;
	}
	/**
	 * Modifica los apellidos de un User
	 */
	public void setLastnames(String lastnames) {
		this.lastnames = lastnames;
	}
	/**
	 * Obtiene el password de un User
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Modifica el password de un User
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Obtiene el rol de un User
	 * @return role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * Modifica el rol de un User
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
}
