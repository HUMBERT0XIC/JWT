package com.mx.Employee.dao;

import com.mx.Employee.entities.Employees;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class EmpleadoDAO {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void insertar(Employees employees) {
        entityManager.persist(employees);
    }

    public boolean esNombreUnico(String name, String lastname) {
        // Verificar si el nombre y apellido son unicos en la base de datos
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(e) FROM Employees e WHERE e.name = :name AND e.last_name = :lastname", Long.class);
        query.setParameter("name", name);
        query.setParameter("lastname", lastname);
        Long count = query.getSingleResult();
        return count == 0;
    }

    public boolean trabajoExiste(Long idJob) {
        // Verificar si el trabajo existe en la base de datos
        return entityManager.find(Employees.class, idJob) != null;
    }

    public List<Employees> getEmployeesByJob(Long jobId) {
        TypedQuery<Employees> query = entityManager.createQuery(
                "SELECT e FROM Employees e WHERE e.jobId = :jobId", Employees.class);
        query.setParameter("jobId", jobId);
        return query.getResultList();
    }

    public List<Employees> getEmployeesByIds(List<Long> id) {
        TypedQuery<Employees> query = entityManager.createQuery(
                "SELECT e FROM Employees e WHERE e.id IN :ids", Employees.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public int getTotalWorkedHours(Long id, Date startDate, Date endDate) {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT SUM(ewh.workedHours) FROM EmployeeWorkedHours ewh " +
                        "WHERE ewh.employeeId = :employeeId " +
                        "AND ewh.workedDate BETWEEN :startDate AND :endDate", Integer.class);
        query.setParameter("id", id);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        Integer totalWorkedHours = query.getSingleResult();
        return totalWorkedHours != null ? totalWorkedHours : 0;
    }

    public boolean employeeExists(Long id) {
        Employees employee = entityManager.find(Employees.class, id);
        return employee != null;
    }
}
