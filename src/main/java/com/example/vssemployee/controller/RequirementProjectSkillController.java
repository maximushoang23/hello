package com.example.vssemployee.controller;

import com.example.vssemployee.entity.RequirementProject;
import com.example.vssemployee.entity.RequirementProjectSkill;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IRequirementProjectService;
import com.example.vssemployee.service.IRequirementProjectSkillService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/31/2021 2:23 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/requirement-project-skills")
public class RequirementProjectSkillController {
    private final static Logger logger = Logger.getLogger(RequirementProjectSkillController.class);
    private IRequirementProjectSkillService iRequirementProjectSkillService;
    private IRequirementProjectService iRequirementProjectService;

    public RequirementProjectSkillController(IRequirementProjectSkillService iRequirementProjectSkillService, IRequirementProjectService iRequirementProjectService) {
        this.iRequirementProjectSkillService = iRequirementProjectSkillService;
        this.iRequirementProjectService = iRequirementProjectService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    @GetMapping()
    public ResponseEntity<Object>  getAllRequirementProjectSkills() {
        List<RequirementProjectSkill> data = null;
        try {
            data = iRequirementProjectSkillService.getAllRequirementProjectSkill();
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
    public ResponseEntity<Object> getRequirementProjectSkillById(@PathVariable("id") Integer id) {
        RequirementProjectSkill data = iRequirementProjectSkillService.getRequirementProjectSkillById(id);
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

    @GetMapping("/combination-skill/{id}")
    public ResponseEntity<Object> getRequirementProjectSkillByCombinationSkillId(@PathVariable("id") Integer id) {
        RequirementProjectSkill data = iRequirementProjectSkillService.getRequirementProjectSkillByCombinationSkillId(id);
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

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getAllRequirementProjectOfAProject(@PathVariable("projectId") Integer projectId) {
        List<RequirementProjectSkill> data = iRequirementProjectSkillService.getAllRequirementProjectSkillOfAProject(projectId);
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

    @PostMapping("/requirement-project/{id}")
    public ResponseEntity<Object> create(@PathVariable("id") Integer id,@RequestBody RequirementProjectSkill request) {
        RequirementProject requirementProject = iRequirementProjectService.getRequirementProjectById(id);
        if(requirementProject != null) {
            request.setRequirementProjectId(id);
            RequirementProjectSkill data = iRequirementProjectSkillService.create(request);
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
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody RequirementProjectSkill request) {
        RequirementProjectSkill requirementProjectSkill = iRequirementProjectSkillService.getRequirementProjectSkillById(id);
        try {
            if(requirementProjectSkill != null) {
                request.setSkillId(requirementProjectSkill.getSkillId() != null ? requirementProjectSkill.getSkillId() : request.getSkillId());
                request.setLevelId(requirementProjectSkill.getLevelId() != null ? requirementProjectSkill.getLevelId() : request.getLevelId());
                meta.setCode(HttpStatus.OK.value());
                meta.setMessage(HttpStatus.OK.getReasonPhrase());
                response.put("meta", meta);
                response.put("data", request);
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
            logger.error("error when update requirement project skill\n" + e);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * delete a requirement project skill by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            iRequirementProjectSkillService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete requirement project skill" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * delete all requirement project skills of all projects
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Object> deleteAllRequirementProjects() {
        iRequirementProjectSkillService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
