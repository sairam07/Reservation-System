package io.reservation.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.reservation.dao.CustomerDAO;
import io.reservation.dao.ReservationDAO;
import io.reservation.exception.AppException;
import io.reservation.model.Customer;
import io.reservation.model.Tab;

@Path("/reservation")
public class ReservationController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Tab> findall(){
		List <Tab> tabs = null;
		System.out.println("I am at findall");
		
		try {
			ReservationDAO rdao = new ReservationDAO();
			tabs = rdao.findall();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return tabs;
	}
	
	@GET
	@Path("/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tab> findone(@PathParam("date") String date){
		List <Tab> tabs = null;
		System.out.println("I am at findone");
		try {
			ReservationDAO rdao = new ReservationDAO();
			tabs = rdao.findone(date);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return tabs;
		
	}

	@POST
	@Path("/{custid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tab create(@PathParam("custid") int custid,Tab tabl){
		
		System.out.println("I am at create");
		
		try {
			ReservationDAO rdao = new ReservationDAO();
			tabl = rdao.create(custid,tabl);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return tabl;
	}
	
	@PUT
	@Path("/{custid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tab update(@PathParam("custid") int custid, Tab tabl){
		System.out.println("At update");
		try {
			ReservationDAO rdao = new ReservationDAO();
			tabl = rdao.update(custid,tabl);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return tabl;
		
		
	}
	
	@DELETE
	@Path("/{custid}")
	public Response delete(@PathParam("custid") int custid){
		System.out.println("At Delete");
		try {
			
			ReservationDAO rdao = new ReservationDAO();
			rdao.delete(custid);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	  return Response.ok().build();
		
	}
	

}
