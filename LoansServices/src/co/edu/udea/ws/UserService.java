package co.edu.udea.ws;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.bl.UserBL;
import co.edu.udea.exception.MyDaoException;

/**
 * Clase que respondera las peticiones web para los metodos de user
 * @author Oscar Zapata, Jonnatan Rï¿½os, Diego ï¿½lvarez
 *
 */
@Path("user")
@Component
public class UserService {
	@Autowired
	UserBL userBL;
	
	/**
	 * Metodo para registrar un usuario
	 * @param username del usuario
	 * @param names o nombres del usuario
	 * @param lastnames o apellidos del usuario
	 * @param password o contraseña del usuario
	 * @param role o rol del usuario
	 */
	@POST
	@Path("registerUser")
	public String registerUser(@QueryParam("username") String username, @QueryParam("names") String names, 
			@QueryParam("lastnames") String lastnames, @QueryParam("password") String password,
			@QueryParam("role") String role) throws RemoteException{
		try{
			userBL.register(username, names, lastnames, password, role);
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return "";
	}
	
	/**
	 * Metodo para logear un usuario
	 * @param usuario del usuario
	 * @param password o contraseña del usuario
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("login")
	public String login(@QueryParam("usuario") String usuario, @QueryParam("password") String password) throws RemoteException{
		try{
			if(userBL.signIn(usuario, password)){
				return "Autenticación exitosa!";
			}else{
				return "Autenticación erronea!";
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
	}
}
