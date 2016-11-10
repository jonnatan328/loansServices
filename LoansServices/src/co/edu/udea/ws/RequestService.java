package co.edu.udea.ws;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	
	/**
	 * Metodo para crear un request en la base de datos 
	 * @return id
	 * @throws ParseException 
	 */
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("new/{dateRequest}/{startTime}/{endTime}/{state}/{device}/{researcher}")
	public String create(@PathParam("dateRequest") String dateRequest, @PathParam("startTime") String startTim, @PathParam("endTime") String endTim, 
						@PathParam("state") String state, @PathParam("device") Long device, @PathParam("researcher") String researcher) throws RemoteException, ParseException {
		Long id = null;
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd"); 
		Date dateReq = df.parse(dateRequest);
		String [] timeSt = startTim.split(":");
		Calendar startTime= new GregorianCalendar();
		startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSt[0]));
		startTime.set(Calendar.MINUTE, Integer.parseInt(timeSt[1]));
		String [] timeEn = endTim.split(":");
		Calendar endTime= new GregorianCalendar();
		endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeEn[0]));
		endTime.set(Calendar.MINUTE, Integer.parseInt(timeEn[1]));
		try{
			id = requestBL.createRequest(dateReq, startTime, endTime, state, device, researcher);
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return ("se ha creado la solicitud de prestamo con id" + id);
	}
	

	/**
	 * Metodo para aceptar una solicitud dado su id 
	 * @return 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("accept/{id}")
	public void accept(@PathParam("id") Long id) throws RemoteException {		
		try{
			requestBL.acceptRequest(id);;
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo para rechazar una solicitud dado su id 
	 * @return 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reject/{id}")
	public void reject(@PathParam("id") Long id) throws RemoteException {		
		try{
			requestBL.rejectRequest(id);;
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo obtener los requests dado la fecha
	 * @return List<RequestWs>
	 * @throws ParseException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byDateRequest/{dateRequest}")
	public List<RequestWs> getByDate(@PathParam("dateRequest") String dateReq) throws RemoteException, ParseException {
		List<RequestWs> resultado = new ArrayList<>();
		List<Request> datos = null;
		String [] date = dateReq.split("-");
		int year = Integer.parseInt(date[0]);
		int month = Integer.parseInt(date[1]);
		int day = Integer.parseInt(date[2]);
		@SuppressWarnings("deprecation")
		Date dateRequest = new Date((year - 1900),(month - 1), day);
		try{
			datos = requestBL.searchRequest(dateRequest);
			for(Request request: datos){
				RequestWs requestWs = new RequestWs(request.getDateRequest(), request.getStartTime(), request.getEndTime(), request.getDevice(), request.getResearcher());
				resultado.add(requestWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
	/**
	 * Metodo obtener los requests dado una hora de inicio
	 * @return List<RequestWs>
	 * @throws RemoteException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byStartTime/{startTime}")
	public List<RequestWs> getByStartTime(@PathParam("startTime") String startTime) throws RemoteException {
		List<RequestWs> resultado = new ArrayList<>();
		List<Request> datos = null;
		Calendar startTim = new GregorianCalendar();
		String [] timeSt = startTime.split(":");
		startTim.clear();
		startTim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSt[0]));
		startTim.set(Calendar.MINUTE, Integer.parseInt(timeSt[1]));
		try{
			datos = requestBL.searchRequest(startTim);
			for(Request request: datos){
				RequestWs requestWs = new RequestWs(request.getDateRequest(), request.getStartTime(), request.getEndTime(), request.getDevice(), request.getResearcher());
				resultado.add(requestWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
	
	/**
	 * Metodo obtener los requests dado el estado
	 * @return List<RequestWs>
	 * @throws ParseException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byState/{state}")
	public List<RequestWs> getByState(@PathParam("state") String state) throws RemoteException {
		List<RequestWs> resultado = new ArrayList<>();
		List<Request> datos = null;
		try{
			datos = requestBL.searchRequest(state);
			for(Request request: datos){
				RequestWs requestWs = new RequestWs(request.getDateRequest(), request.getStartTime(), request.getEndTime(), request.getDevice(), request.getResearcher());
				resultado.add(requestWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
	
	/**
	 * Metodo obtener los requests dado un dispositivo
	 * @return List<RequestWs>
	 * @throws ParseException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("byDevice/{device}")
	public List<RequestWs> getByDevice(@PathParam("device") Long device) throws RemoteException {
		List<RequestWs> resultado = new ArrayList<>();
		List<Request> datos = null;
		try{
			datos = requestBL.searchRequest(device);
			for(Request request: datos){
				RequestWs requestWs = new RequestWs(request.getDateRequest(), request.getStartTime(), request.getEndTime(), request.getDevice(), request.getResearcher());
				resultado.add(requestWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
	
	/**
	 * Metodo que valida que el prestamo no supere 8 horas
	 * @return List<RequestWs>
	 * @throws ParseException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("validateTimes/{startTime}/{endTime}")
	public String validateTimes(@PathParam("startTime") String startTim, @PathParam("endTime") String endTim) throws RemoteException {
		boolean validate;
		String [] timeSt = startTim.split(":");
		Calendar startTime= new GregorianCalendar();
		startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSt[0]));
		startTime.set(Calendar.MINUTE, Integer.parseInt(timeSt[1]));
		String [] timeEn = endTim.split(":");
		Calendar endTime= new GregorianCalendar();
		endTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeEn[0]));
		endTime.set(Calendar.MINUTE, Integer.parseInt(timeEn[1]));
		try{
			validate = requestBL.validateTime(startTime, endTime);
			if(validate){
				return ("Es posible hacer el prestamo");
			}else{
				return ("No es posible realizar el prestamo en mas de 8 horas");
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		
	}
	
}
