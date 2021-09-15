package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.EmployeeExpectedSkill;
import com.example.vssemployee.entity.EmployeeSkill;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IEmployeeExpectedSkillService;
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
 * created in 8/31/2021 5:00 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/employee-expectedskills")
public class EmployeeExpectedSkillController {
    private final static Logger logger = Logger.getLogger(EmployeeExpectedSkillController.class);
    private IEmployeeExpectedSkillService iEmployeeExpectedSkillService;
    private IEmployeeService iEmployeeService;

    public EmployeeExpectedSkillController(IEmployeeExpectedSkillService iEmployeeExpectedSkillService,
                                           IEmployeeService iEmployeeService) {
        this.iEmployeeExpectedSkillService = iEmployeeExpectedSkillService;
        this.iEmployeeService = iEmployeeService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    @GetMapping()
    public ResponseEntity<Object> getAlls() {
        List<EmployeeExpectedSkill> data = null;
        try {
            data = iEmployeeExpectedSkillService.getAlls();
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
        EmployeeExpectedSkill data = iEmployeeExpectedSkillService.getById(id);
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
        List<EmployeeExpectedSkill> data = iEmployeeExpectedSkillService.getByEmployeeId(employeeId);
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

    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<Object> create(@PathVariable("employeeId") Integer employeeId,@RequestBody EmployeeExpectedSkill employeeExpectedSkillRequest) {
        Employee employee = iEmployeeService.getById(employeeId);
        if(employee != null) {
            employeeExpectedSkillRequest.setEmployeeId(employeeId);
            EmployeeExpectedSkill data = iEmployeeExpectedSkillService.create(employeeExpectedSkillRequest);
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody EmployeeExpectedSkill employeeExpectedSkillRequest) {
        EmployeeExpectedSkill employeeExpectedSkill = iEmployeeExpectedSkillService.getById(id);
        if(employeeExpectedSkill != null ) {
            employeeExpectedSkill.setEmployeeId(employeeExpectedSkillRequest.getEmployeeId() != null ? employeeExpectedSkillRequest.getEmployeeId() : employeeExpectedSkill.getEmployeeId());
            employeeExpectedSkill.setSkillIdExpected(employeeExpectedSkillRequest.getSkillIdExpected() != null ? employeeExpectedSkillRequest.getSkillIdExpected() : employeeExpectedSkill.getSkillIdExpected());
            employeeExpectedSkill.setLevelIdExpected(employeeExpectedSkillRequest.getLevelIdExpected() != null ? employeeExpectedSkillRequest.getLevelIdExpected() : employeeExpectedSkill.getLevelIdExpected());
            employeeExpectedSkillRequest = iEmployeeExpectedSkillService.update(employeeExpectedSkill);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", employeeExpectedSkillRequest);
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
            iEmployeeExpectedSkillService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete employee expectedskill" + e);
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
                Integer check = iEmployeeExpectedSkillService.deleteByEmployeeId(employeeId);
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
        iEmployeeExpectedSkillService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
