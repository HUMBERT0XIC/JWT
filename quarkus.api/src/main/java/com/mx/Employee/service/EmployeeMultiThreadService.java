package com.mx.Employee.service;

import com.mx.Employee.dao.EmpleadoDAO;
import com.mx.Employee.entities.Employees;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeMultiThreadService {

    @Inject
    EmpleadoDAO empleadoDAO;

    public List<Employees> getEmployeesInfo(List<Long> id) {
        // Dividir la lista de IDs en lotes mas pequenios para procesamiento multihilo
        List<List<Long>> batches = partitionList(id, 10); // Divide en lotes de 10 IDs

        // Procesar cada lote en paralelo
        List<CompletableFuture<List<Employees>>> futures = batches.stream()
                .map(batch -> CompletableFuture.supplyAsync(() -> empleadoDAO.getEmployeesByIds(batch)))
                .collect(Collectors.toList());

        // Combinar los resultados de todos los lotes
        return futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Metdo de utilidad para dividir una lista en lotes mas pequeños
    private <T> List<List<T>> partitionList(List<T> list, int batchSize) {
        return new ArrayList<>(list.stream()
                .collect(Collectors.groupingBy(e -> list.indexOf(e) / batchSize))
                .values());
    }
}
