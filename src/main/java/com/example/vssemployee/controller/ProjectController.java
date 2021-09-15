package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Project;
import com.example.vssemployee.entity.Skill;
import com.example.vssemployee.repository.ProjectRepository;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IProjectService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author manhdt14
 * created in 8/31/2021 11:52 AM
 */

@RestController
@CrossOrigin
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private static final Logger logger = Logger.getLogger(ProjectController.class);
    private IProjectService iProjectService;

    @GetMapping("/custumerid/{custumerid}")
    public ResponseEntity<Object> getCustomerByCustomerId(@PathVariable(name = "custumerid") Integer custumerid){
        List<Project> data = iProjectService.getProjectByCustomerId(custumerid);
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


    public ProjectController(IProjectService iProjectService) {
        this.iProjectService = iProjectService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    /**
     * get all projects
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> getAllProjects() {
        try {
            List<Project> data = iProjectService.getAllProjects();
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
     * get project by id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getSkillById(@PathVariable("id") Integer id) {
        Project data = iProjectService.getProjectById(id);
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
     * get project by name
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSkillByName(@PathVariable("name") String name) {
        List<Project> data = iProjectService.getProjectByName(name);
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
     * create a project
     * @param project
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> addProject(@RequestBody Project project) {
        Project data = iProjectService.created(project);
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
     * update a project by id
     * @param id
     * @param projectRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") Integer id, @RequestBody Project projectRequest) {
        Project project = iProjectService.getProjectById(id);
        if(project != null ) {
            project.setName(projectRequest.getName() != null ? projectRequest.getName() : null);
            project.setCustomerId(projectRequest.getCustomerId() != null ? projectRequest.getCustomerId() :null);
            project.setStartedAt(projectRequest.getStartedAt() != null ? projectRequest.getStartedAt() : null);
            project.setEndedAt(projectRequest.getEndedAt() != null ? projectRequest.getEndedAt() : null);
            project.setEffort(projectRequest.getEffort() != null ? projectRequest.getEffort() : null);
            project.setStatus(projectRequest.getStatus() != null ? projectRequest.getStatus() : null) ;
            projectRequest = iProjectService.update(project);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", projectRequest);
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
     * delete a project by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable("id") Integer id) {
        try {
            iProjectService.deleteById(id);
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

    @DeleteMapping()
    public ResponseEntity<Object> deleteAllProjects() {
        iProjectService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
