package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Department;
import com.example.vssemployee.entity.Skill;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IDepartmentService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/31/2021 9:27 AM
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/departments")
public class DepartmentController {
    private final static Logger logger = Logger.getLogger(DepartmentController.class);
    private IDepartmentService iDepartmentService;

    public DepartmentController(IDepartmentService iDepartmentService) {
        this.iDepartmentService = iDepartmentService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();
    @GetMapping()
    public ResponseEntity<Object> getAllDepartments() {
        List<Department> data = null;
        try {
            data = iDepartmentService.getAllDepartments();
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
    public ResponseEntity<Object> getDepartmentById(@PathVariable("id") Integer id) {
        Department data = iDepartmentService.getDepartmentById(id);
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
        List<Department> data = iDepartmentService.getDepartmentByName(name);
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
    public ResponseEntity<Object> addDepartment(@RequestBody Department department) {
        Department data = iDepartmentService.create(department);
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
    public ResponseEntity<Object> updateDepartment(@PathVariable("id") Integer id, @RequestBody Department departmentRequest) {
        Department skill = iDepartmentService.getDepartmentById(id);
        if(skill != null) {
            skill.setName(departmentRequest.getName());
            departmentRequest = iDepartmentService.update(skill);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", departmentRequest);
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
    public ResponseEntity<Object> deleteSkill(@PathVariable("id") Integer id) {
        try {
            iDepartmentService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete skill" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping()
    public ResponseEntity<Object> deleteAllSkills() {
        iDepartmentService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
