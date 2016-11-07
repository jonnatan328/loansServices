package co.edu.udea.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.bl.RequestBL;
import co.edu.udea.dto.Request;
import co.edu.udea.exception.MyDaoException;
import co.edu.udea.ws.dto.RequestWs;



/**
 * Clase que respondera las peticiones web para los metodos de request
 * @author Oscar Zapata, Jonnatan R�os, Diego �lvarez
 *
 */
@Path("request")
@Component
public class RequestService {
	@Autowired
	RequestBL requestBL;
	
	
	/**
	 * Metodo para crear un request en la base de datos 
	 * @return id
	 */
	/*
	@POST
	@Produces("text/html")
	@Path("new")
	public String create(@PathParam("dateRequest")Date dateRequest, @PathParam("startTime")Calendar startTime, @PathParam("endTime")Calendar endTime, 
						@PathParam("state")String state, @PathParam("device")Long device, @PathParam("researcher")String researcher) throws RemoteException {
		Long id = null;
		try{
			id = requestBL.createRequest(dateRequest, startTime, endTime, state, device, researcher);
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return "se ha creado la solicitud de prestamo con id" + id;
	}
	*/
	/**
	 * Metodo obtener los requests 
	 * @return List<RequestWs>
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public List<RequestWs> get() throws RemoteException {
		List<RequestWs> resultado = new ArrayList<>();
		List<Request> datos = null;
		
		try{
			datos = requestBL.showRequests();
			for(Request request: datos){
				RequestWs requestWs = new RequestWs(request.getDateRequest(), request.getStartTime(), request.getEndTime(), request.getDevice(), request.getResearcher());
				resultado.add(requestWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
}
