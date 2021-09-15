package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.Level;
import com.example.vssemployee.entity.Project;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IEmployeeService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/31/2021 11:05 AM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final static Logger logger = Logger.getLogger(EmployeeController.class);
    private IEmployeeService iEmployeeService;

    public EmployeeController(IEmployeeService iEmployeeService) {
        this.iEmployeeService = iEmployeeService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    @GetMapping()
    public ResponseEntity<Object> getAlls() {
        List<Employee> data = null;
        try {
            data = iEmployeeService.getAlls();
            if(data != null) {
                meta.setCode(HttpStatus.OK.value());
                meta.setMessage(HttpStatus.OK.getReasonPhrase());
                response.put("meta", meta);
                response.put("data", data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                meta.setCode(HttpStatus.NO_CONTENT.value());
                meta.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
                response.put("meta", meta);
                response.put("data", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        Employee data = iEmployeeService.getById(id);
        if(data != null) {
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getDepartmentByName(@PathVariable("name") String name) {
        List<Employee> data = iEmployeeService.getByName(name);
        if(data.size() > 0) {
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody Employee employee) {
        Employee data = iEmployeeService.create(employee);
        if(data != null) {
            meta.setCode(HttpStatus.CREATED.value());
            meta.setMessage(HttpStatus.CREATED.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody Employee employeeRequest) {
        Employee employee = iEmployeeService.getById(id);
        if(employee != null ) {
            employee.setName(employeeRequest.getName() != null ? employeeRequest.getName() : null);
            employee.setBirthday(employeeRequest.getBirthday() != null ? employeeRequest.getBirthday() : null);
            employee.setLocation(employeeRequest.getLocation() != null ? employeeRequest.getLocation() : null);
            employee.setMaritalStatus(employeeRequest.getMaritalStatus() != null ? employeeRequest.getMaritalStatus() : null);
            employee.setEmail(employeeRequest.getEmail() != null ? employeeRequest.getEmail() : null);
            employee.setDepartmentId(employeeRequest.getDepartmentId() != null ? employeeRequest.getDepartmentId() : null);
            employee.setUserCode(employeeRequest.getUserCode() != null ? employeeRequest.getUserCode() : null);
            employeeRequest = iEmployeeService.update(employee);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", employeeRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            iEmployeeService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete employee" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteAllSkills() {
        iEmployeeService.deleteAlls();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
