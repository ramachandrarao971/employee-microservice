package com.employee.service;

import com.employee.repository.EmployeeRepository;
import com.employee.model.Employee;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class EmployeeService {

    @Value("${payroll.service}")
    private String payrollServiceUrl;
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    private boolean updateOnPayrollMonthSalary(String employeeId, Double ctc) {
        System.out.println(employeeId + ctc);
        String url = payrollServiceUrl + employeeId;
        System.out.println("URL " + url);
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("employeeId", employeeId);
        requestBody.put("ctc", ctc);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return true;
        }

        return false;
    }

    public Employee updateEmployee(String id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        if (employee.getFirstName() != null) {
            existingEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            existingEmployee.setLastName(employee.getLastName());
        }
        if (employee.getAge() != 0) {
            existingEmployee.setAge(employee.getAge());
        }
        if (employee.getCtc() != null) {
            existingEmployee.setCtc(employee.getCtc());
            this.updateOnPayrollMonthSalary(existingEmployee.getId(), employee.getCtc());
        }
        if (employee.getOrganisation() != null) {
            existingEmployee.setOrganisation(employee.getOrganisation());
        }
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

