package com.mx.Employee.service;

import com.mx.Employee.dao.EmpleadoDAO;
import com.mx.Employee.entities.Employees;
import com.mx.Employee.model.RequestJob;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Date; // Importa la clase Date para trabajar con fechas
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService {
    @Inject
    EmpleadoDAO empleadoDAO;

    public boolean agregarEmpleado(Employees employees) {
        if (esNombreUnico(employees.getName(), employees.getLastName())
                && esMayorDeEdad(employees.getBirthdate())
                && trabajoExiste(employees.getJobId())) {
            empleadoDAO.insertar(employees);
            return true;
        }
        return false;
    }

    private boolean esNombreUnico(String name, String lastname) {
        return empleadoDAO.esNombreUnico(name, lastname);
    }

    private boolean esMayorDeEdad(Date birthdate) {
        Date fechaActual = new Date();
        int edadMinima = 18;
        // Calcula la diferencia en milisegundos entre la fecha actual y la fecha de nacimiento
        long diferencia = fechaActual.getTime() - birthdate.getTime();
        // Convierte la diferencia a anios dividiendo por los milisegundos en un año
        int edad = (int) (diferencia / (1000L * 60 * 60 * 24 * 365));
        return edad >= edadMinima;
    }

    private boolean trabajoExiste(Long idJob) {
        return empleadoDAO.trabajoExiste(idJob);
    }

    public List<Employees> getEmployeesByJob(Long jobId) {
        // Consultar los empleados por el ID del puesto recibido
        List<Employees> employees = empleadoDAO.getEmployeesByJob(jobId);

        // Filtrar los empleados por el puesto recibido usando expresiones lambda
        employees = employees.stream()
                .filter(employee -> employee.getJobId().equals(jobId))
                .collect(Collectors.toList());

        // Ordenar los empleados por apellido materno
        employees.sort(Comparator.comparing(Employees::getLastName));

        // Agrupar los empleados por apellido materno respetando las tablas proporcionadas
        Map<String, List<Employees>> employeesByLastName = employees.stream()
                .collect(Collectors.groupingBy(Employees::getLastName));

        return employees;
    }

    public int getTotalWorkedHours(Long id, Date startDate, Date endDate) {
        // Validar que el empleado exista
        if (!empleadoDAO.employeeExists(id)) {
            throw new IllegalArgumentException("El empleado con ID " + id + " no existe.");
        }

        // Validar que la fecha de inicio sea menor a la fecha de fin
        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser menor o igual a la fecha de fin.");
        }

        // Obtener el total de horas trabajadas
        return empleadoDAO.getTotalWorkedHours(id, startDate, endDate);
    }
}
