package com.mx.Employee.controller;

import com.mx.Employee.entities.Employees;
import com.mx.Employee.model.Request;
import com.mx.Employee.model.Response;
import com.mx.Employee.service.EmployeeMultiThreadService;
import com.mx.Employee.service.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {
    @Inject
    EmployeeService employeeService;

    @Inject
    EmployeeMultiThreadService employeeMultiThreadService;

    // Inserciones
    @POST()
    public Response addEmployee(Request request) {
        Employees employee = new Employees();
        employee.setGenderId(request.getGenderId());
        employee.setJobId(request.getJobId());
        employee.setName(request.getName());
        employee.setLastName(request.getLastName());
        employee.setBirthdate(request.getBirthdate());

        boolean success = employeeService.agregarEmpleado(employee);

        Response response = new Response();
        response.setId(success ? employee.getId() : null);
        response.setSuccess(success);

        return response;
    }

    // Metodo para consultar empleados por puesto y aplicar filtros y ordenamientos
    @GET
    @Path("/byjob/{jobId}")
    public List<Employees> getEmployeesByJob(@PathParam("jobId") Long jobId) {
        return employeeService.getEmployeesByJob(jobId);
    }

    // Metodo para consulta multi hilos
    @POST
    @Path("/info")
    public List<Employees> getEmployeesInfo(List<Long> employeeIds) {
        return employeeMultiThreadService.getEmployeesInfo(employeeIds);
    }

    // Filtrado por hrs
    @GET
    @Path("/{id}/worked-hours")
    public int getTotalWorkedHours(
            @PathParam("id") Long id,
            @QueryParam("startDate") Long startDate,
            @QueryParam("endDate") Long endDate
    ) {
        // Convertir las fechas de milisegundos a objetos Date
        Date start = new Date(startDate);
        Date end = new Date(endDate);

        return employeeService.getTotalWorkedHours(id, start, end);
    }

}
