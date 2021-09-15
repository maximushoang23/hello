package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Employee;
import com.example.vssemployee.entity.EmployeeRole;
import com.example.vssemployee.entity.EmployeeSkill;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IEmployeeService;
import com.example.vssemployee.service.IEmployeeSkillService;
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
@RequestMapping("/api/v1/employee-skills")
public class EmployeeSkillController {
    private final static Logger logger = Logger.getLogger(EmployeeSkillController.class);
    private IEmployeeSkillService iEmployeeSkillService;
    private IEmployeeService iEmployeeService;
    public EmployeeSkillController(IEmployeeSkillService iEmployeeSkillService, IEmployeeService iEmployeeService) {
        this.iEmployeeSkillService = iEmployeeSkillService;
        this.iEmployeeService = iEmployeeService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    @GetMapping()
    public ResponseEntity<Object> getAlls() {
        List<EmployeeSkill> data = null;
        try {
            data = iEmployeeSkillService.getAlls();
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
        EmployeeSkill data = iEmployeeSkillService.getById(id);
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
        List<EmployeeSkill> data = iEmployeeSkillService.getByEmployeeId(employeeId);
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
    public ResponseEntity<Object> create(@PathVariable("employeeId") Integer employeeId,@RequestBody EmployeeSkill employeeSkillRequest) {
        Employee employee = iEmployeeService.getById(employeeId);
        if(employee != null) {
            employeeSkillRequest.setEmployeeId(employeeId);
            EmployeeSkill data = iEmployeeSkillService.create(employeeSkillRequest);
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
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody EmployeeSkill employeeSkillRequest) {
        EmployeeSkill employeeSkill = iEmployeeSkillService.getById(id);
        if(employeeSkill != null ) {
            employeeSkill.setEmployeeId(employeeSkillRequest.getEmployeeId() != null ? employeeSkillRequest.getEmployeeId() : employeeSkill.getEmployeeId());
            employeeSkill.setLevelId(employeeSkillRequest.getLevelId() != null ? employeeSkillRequest.getLevelId() : employeeSkill.getLevelId());
            employeeSkill.setSkillId(employeeSkillRequest.getSkillId() != null ? employeeSkillRequest.getSkillId() : employeeSkill.getSkillId());
            employeeSkill.setInterest(employeeSkillRequest.getInterest() != null ? employeeSkillRequest.getInterest() : employeeSkill.getInterest());
            employeeSkill.setCertificate(employeeSkillRequest.getCertificate() != null ? employeeSkillRequest.getCertificate() : employeeSkill.getCertificate());
            employeeSkill.setCoreCompetence(employeeSkillRequest.getCoreCompetence() != null ? employeeSkillRequest.getCoreCompetence() : employeeSkill.getCoreCompetence());
            employeeSkillRequest = iEmployeeSkillService.update(employeeSkill);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", employeeSkillRequest);
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
            iEmployeeSkillService.deleteById(id);
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
                Integer check = iEmployeeSkillService.deleteByEmployeeId(employeeId);
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
        iEmployeeSkillService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
