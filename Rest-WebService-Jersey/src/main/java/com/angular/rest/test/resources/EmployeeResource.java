package com.angular.rest.test.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;

import com.angular.rest.test.cors.impl.CORSFilter;
import com.angular.rest.test.model.Employee;
import com.angular.rest.test.services.EmployeeService;


@Path("/Employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	
	final ResourceConfig rc=new ResourceConfig();
	CORSFilter cf=new CORSFilter();
	
	EmployeeService empService=new EmployeeService();
	
	public EmployeeResource() {
		System.out.println("rc "+rc);
		rc.register(com.angular.rest.test.cors.impl.CORSFilter.class);
		try {
			System.out.println("Registering corsfilter");
			Class.forName("com.angular.rest.test.cors.impl.CORSFilter");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@GET
	public Response getEmployees() {
		List<Employee> emps=empService.getAllEmployees();
		GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(emps) {};
		return Response.ok()
				.entity(entity)
				.header("Access-Control-Allow-Origin","*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .allow("OPTIONS")
				.build();
	//	return empService.getAllEmployees();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(@PathParam("id") int id) {
		return empService.getEmployee(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addEmployee(Employee employee) {
		return empService.addEmployee(employee);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateEmployee(@PathParam("id") int id,Employee employee) {
		return empService.updateEmployee(employee);
	}
	
	@GET
	@Path("/check/{uname}")
	public Response checkCredentails(@PathParam("uname")String uname) {
		Employee e=empService.checkCredentials(uname);
		return Response.ok()
				.entity(e)
				.header("Access-Control-Allow-Origin","*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .allow("OPTIONS")
				.build();
//		return empService.checkCredentials(uname);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addOrder")
	public Response addOrder() {
	    return Response.ok()
	            .header("Access-Control-Allow-Origin","*")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	            .allow("OPTIONS")
	            .build();
	}
	
}
