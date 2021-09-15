package com.example.vssemployee.controller;

import com.example.vssemployee.entity.*;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IEmployeeRoleService;
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
 * created in 8/31/2021 4:59 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/employee-roles")
public class EmployeeRoleController {
    private final static Logger logger = Logger.getLogger(EmployeeRoleController.class);
    private IEmployeeRoleService iEmployeeRoleService;
    private IEmployeeService iEmployeeService;
    public EmployeeRoleController(IEmployeeRoleService iEmployeeRoleService, IEmployeeService iEmployeeService) {
        this.iEmployeeRoleService =iEmployeeRoleService;
        this.iEmployeeService = iEmployeeService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    @GetMapping()
    public ResponseEntity<Object> getAlls() {
        List<EmployeeRole> data = null;
        try {
            data = iEmployeeRoleService.getAlls();
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
        EmployeeRole data = iEmployeeRoleService.getById(id);
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

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Object> getByEmployeeId(@PathVariable("employeeId") Integer employeeId) {
        List<EmployeeRole> data = iEmployeeRoleService.getByEmployeeId(employeeId);
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody EmployeeRole employeeRoleRequest) {
        EmployeeRole employeeRole = iEmployeeRoleService.getById(id);
        if(employeeRole != null ) {
            employeeRole.setRoleId(employeeRoleRequest.getRoleId() != null ? employeeRoleRequest.getRoleId() : employeeRole.getRoleId());
            employeeRole.setEmployeeId(employeeRoleRequest.getEmployeeId() != null ? employeeRoleRequest.getEmployeeId() : employeeRole.getEmployeeId());
            employeeRole.setLevelId(employeeRoleRequest.getLevelId() != null ? employeeRoleRequest.getLevelId() : employeeRole.getLevelId());
            employeeRoleRequest = iEmployeeRoleService.update(employeeRole);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", employeeRoleRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<Object> create(@PathVariable("employeeId") Integer employeeId,@RequestBody EmployeeRole employeeRoleRequest) {
        Employee employee = iEmployeeService.getById(employeeId);
        if(employee != null) {
            employeeRoleRequest.setEmployeeId(employeeId);
            EmployeeRole data = iEmployeeRoleService.create(employeeRoleRequest);
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
            iEmployeeRoleService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete employee role" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Object> deleteAllRequimentProjectOfAProject(@PathVariable("employeeId") Integer employeeId) {
        try {
            Employee employee = iEmployeeService.getById(employeeId);
            if(employee != null) {
                Integer check = iEmployeeRoleService.deleteByEmployeeId(employeeId);
                if(check != 0) {
                    meta.setCode(HttpStatus.OK.value());
                    meta.setMessage(HttpStatus.OK.getReasonPhrase());
                    response.put("meta", meta);
                    response.put("data", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    meta.setCode(HttpStatus.NOT_FOUND.value());
                    meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                    response.put("meta", meta);
                    response.put("data", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                meta.setCode(HttpStatus.NOT_FOUND.value());
                meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
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

    @DeleteMapping
    public ResponseEntity<Object> deleteAllRequirementProjects() {
        iEmployeeRoleService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
