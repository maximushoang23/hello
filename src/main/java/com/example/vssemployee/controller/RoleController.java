package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Role;
import com.example.vssemployee.entity.Skill;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IRoleService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/31/2021 9:12 AM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final static Logger logger = Logger.getLogger(RoleController.class);
    private IRoleService iRoleService;

    public RoleController(IRoleService iRoleService) {
        this.iRoleService = iRoleService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    /**
     * get all roles
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> getAllRoles() {
        List<Role> data = null;
        try {
            data = iRoleService.getAllRoles();
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

    /**
     * get role by id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getSkillById(@PathVariable("id") Integer id) {
        Role data = iRoleService.getRoleById(id);
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

    /**
     * get role by name
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSkillByName(@PathVariable("name") String name) {
        List<Role> data = iRoleService.getRoleByName(name);
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

    /**
     * create a role
     * @param role
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> addRole(@RequestBody Role role) {
        Role data = iRoleService.created(role);
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

    /**
     * update name of role by id
     * @param id
     * @param roleRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRole(@PathVariable("id") Integer id, @RequestBody Role roleRequest) {
        Role role = iRoleService.getRoleById(id);
        if(role != null) {
            role.setName(roleRequest.getName());
            roleRequest = iRoleService.update(role);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", roleRequest);
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
    public ResponseEntity<Object> deleteRole(@PathVariable("id") Integer id) {
        try {
            iRoleService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete role" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * delele all roles
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<Object> deleteAllRoles() {
        iRoleService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
