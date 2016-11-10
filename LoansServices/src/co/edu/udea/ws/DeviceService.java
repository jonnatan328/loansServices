package co.edu.udea.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.bl.DeviceBL;
import co.edu.udea.dto.Device;
import co.edu.udea.exception.MyDaoException;
import co.edu.udea.ws.dto.DeviceWs;

/**
 * Clase que respondera las peticiones web para los metodos de device
 * @author Oscar Zapata, Jonnatan R�os, Diego �lvarez
 *
 */
@Path("device")
@Component
public class DeviceService {
	@Autowired
	DeviceBL deviceBL;
	
	/**
	 * Metodo para crear un dispositivo
	 * @param name o nombre del dispositivo.
	 * @param numero o cantidad de dipositivos.
	 */
	@POST
	@Path("createDevice")
	public String createDevice(@QueryParam("name") String name, @QueryParam("number") int number) throws RemoteException{
		try{
			deviceBL.createDevice(name, number);
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return "";
	}
	
	/**
	 * Metodo para eliminar un dispositivo
	 * @param id del dispositivo.
	 */
	@POST
	@Path("deleteDevice")
	public String deleteDevice(@QueryParam("id") Long id) throws RemoteException{
		try{
			deviceBL.deleteDevice(id);
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return "";
	}
	
	/**
	 * Metodo obtener los dispositivos 
	 * @return List<DeviceWs>
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getAll")
	public List<DeviceWs> get() throws RemoteException {
		List<DeviceWs> resultado = new ArrayList<>();
		List<Device> datos = null;
		
		try{
			datos = deviceBL.showDevices();
			for(Device device: datos){
				DeviceWs deviceWs = new DeviceWs(device.getId(), device.getName(), device.getNumber(), device.getState());
				resultado.add(deviceWs);
			}
		}catch(MyDaoException e){
			throw new RemoteException(e.getMessage(), e);
		}
		return resultado;
	}
}
