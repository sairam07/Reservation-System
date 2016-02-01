package io.reservation.controller;

import io.reservation.dao.CustomerDAO;
import io.reservation.exception.AppException;
import io.reservation.model.Customer;

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

@Path("/customer")
public class CustomerController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Customer> findall(){
		List <Customer> customers = null;
		System.out.println("I am at findall");
		
		try {
			CustomerDAO cdao = new CustomerDAO();
			customers = cdao.findall();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return customers;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findone(@PathParam("id") int id){
		Customer ct = null;
		System.out.println("I am at findone");
		System.out.println(id);
		try {
			CustomerDAO cdao = new CustomerDAO();
			ct = cdao.findone(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return ct;
		
	}
	
		@GET
		@Path("/status")
		@Produces(MediaType.APPLICATION_JSON)
		public List <Customer> findstatus(){
			List <Customer> customers = null;
			System.out.println("I am at findstatus");
			
			try {
				CustomerDAO cdao = new CustomerDAO();
				customers = cdao.findstatus();
			} catch (AppException e) {
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
			return customers;
		}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer create(Customer cst){
		
		System.out.println("I am at create");
		
		try {
			CustomerDAO cdao = new CustomerDAO();
			cst = cdao.create(cst);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return cst;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer update(@PathParam("id") int id, Customer cst){
		System.out.println("At update");
		try {
			CustomerDAO cdao = new CustomerDAO();
			cst = cdao.update(id,cst);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
		return cst;
		
		
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		System.out.println("At Delete");
		try {
			
			CustomerDAO cdao = new CustomerDAO();
			cdao.delete(id);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	  return Response.ok().build();
		
	}
}
