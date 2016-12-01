package co.edu.udea.ws;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.bl.UserBL;
import co.edu.udea.dto.User;
import co.edu.udea.exception.MyDaoException;
import co.edu.udea.ws.dto.UserWs;

/**
 * Clase que respondera las peticiones web para los metodos de user
 * @author Oscar Zapata, Jonnatan R�os, Diego �lvarez
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
	 * @param password o contrase�a del usuario
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
	 * @param password o contrase�a del usuario
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Object login(@QueryParam("usuario") String usuario, @QueryParam("password") String password) throws RemoteException{
		JSONObject response = null;
		try{
			if(userBL.signIn(usuario, password)){
				//return "Autenticaci�n exitosa!";
				response = new JSONObject();
				response.put("authenticated", true);				
				return response;
			}else{
				//return "Autenticaci�n erronea!";
				response = new JSONObject();
				response.put("authenticated", false);				
				return response;			}
		}catch(MyDaoException | JSONException e){
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo para obtener un usuario
	 * @param usuario del usuario
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user")
	public UserWs getUser(@QueryParam("usuario") String usuario) throws RemoteException{
		UserWs userWs = null;

		try{
			User user = userBL.getUser(usuario);
			userWs = new UserWs();
			userWs.setUsername(user.getUsername());
			userWs.setNames(user.getNames());
			userWs.setLastnames(user.getLastnames());
			userWs.setRole(user.getRole());			
			
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return userWs;
	}
}
