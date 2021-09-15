package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Department;
import com.example.vssemployee.entity.Project;
import com.example.vssemployee.entity.RequirementProject;
import com.example.vssemployee.entity.Role;
import com.example.vssemployee.repository.ProjectRepository;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IDepartmentService;
import com.example.vssemployee.service.IProjectService;
import com.example.vssemployee.service.IRequirementProjectService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/31/2021 1:47 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/requirement-projects")
public class RequirementProjectController {
    private final static Logger logger = Logger.getLogger(RequirementProjectController.class);
    private IRequirementProjectService iRequirementProjectService;
    private IProjectService iProjectService;

    public RequirementProjectController(IRequirementProjectService iRequirementProjectService,
                                        IProjectService iProjectService) {
        this.iRequirementProjectService = iRequirementProjectService;
        this.iProjectService = iProjectService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    /**
     * get all requirements project of all projects
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> getAllRequirementProjects() {
        List<RequirementProject> data = null;
        try {
            data = iRequirementProjectService.getAllRequirementProjects();
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
     * get requirement project by id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getRequirementProjectById(@PathVariable("id") Integer id) {
        RequirementProject data = iRequirementProjectService.getRequirementProjectById(id);
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
     * get all requirement project of a project by project id
     * @param projectId
     * @return
     */
    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getAllRequirementProjectOfAProject(@PathVariable("projectId") Integer projectId) {
        List<RequirementProject> data = iRequirementProjectService.getAllRequirementProjectOfAProject(projectId);
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
     * update requirement project of a project
     * @param id
     * @param requirementProjectRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRequirementProject(@PathVariable("id") Integer id, @RequestBody RequirementProject requirementProjectRequest) {
        RequirementProject requirementProject = iRequirementProjectService.getRequirementProjectById(id);
        try {
            if(requirementProject != null) {
                requirementProject.setProjectId(requirementProjectRequest.getProjectId() != null ? requirementProjectRequest.getProjectId() : null);
                requirementProject.setRoleId(requirementProjectRequest.getRoleId() != null ? requirementProjectRequest.getRoleId() : null);
                requirementProject.setLevelId(requirementProjectRequest.getLevelId() != null ? requirementProjectRequest.getLevelId() : null);
                requirementProjectRequest = iRequirementProjectService.updateRequirementProject(requirementProject);
                meta.setCode(HttpStatus.OK.value());
                meta.setMessage(HttpStatus.OK.getReasonPhrase());
                response.put("meta", meta);
                response.put("data", requirementProjectRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
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
            logger.error("error when update requirement project\n" + e);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * create requirement project of a existed project
     * @param projectId
     * @param requirementProjectRequest
     * @return
     */
    @PostMapping("/project/{projectId}")
    public ResponseEntity<Object> createRequirementProject(@PathVariable("projectId") Integer projectId,@RequestBody RequirementProject requirementProjectRequest) {
        Project project = iProjectService.getProjectById(projectId);
        if(project != null) {
                requirementProjectRequest.setProjectId(projectId);
                RequirementProject data = iRequirementProjectService.create(requirementProjectRequest);
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

    /**
     * delete a requirement project
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            iRequirementProjectService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete requirement project" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * delete all requirement project by project id
     * @param projectId
     * @return
     */
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<Object> deleteAllRequimentProjectOfAProject(@PathVariable("projectId") Integer projectId) {
        try {
            Project project = iProjectService.getProjectById(projectId);
            if(project != null) {
                Integer check = iRequirementProjectService.deleteByProjectIdIs(projectId);
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

    /**
     * delete all requirements project of all projects
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteAllRequirementProjects() {
        iRequirementProjectService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
