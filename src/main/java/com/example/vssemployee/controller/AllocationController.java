package com.example.vssemployee.controller;

import com.example.vssemployee.entity.*;
import com.example.vssemployee.repository.EmployeeRepository;
import com.example.vssemployee.repository.EmployeeRoleRepository;
import com.example.vssemployee.repository.EmployeeSkillRepository;
import com.example.vssemployee.response.Meta;
import com.example.vssemployee.service.IAllocationService;
import com.example.vssemployee.service.IRequirementProjectService;
import com.example.vssemployee.service.IRequirementProjectSkillService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author manhdt14
 * created in 8/31/2021 2:29 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/allocations")
public class AllocationController {
    private final static Logger logger = Logger.getLogger(AllocationController.class);
    private IAllocationService iAllocationService;
    private IRequirementProjectSkillService iRequirementProjectSkillService;
    private IRequirementProjectService iRequirementProjectService;

    private EmployeeRoleRepository employeeRoleRepository;
    private EmployeeSkillRepository employeeSkillRepository;
    private EmployeeRepository employeeRepository;

    public AllocationController(IAllocationService iAllocationService, IRequirementProjectSkillService iRequirementProjectSkillService, IRequirementProjectService iRequirementProjectService, EmployeeRoleRepository employeeRoleRepository, EmployeeSkillRepository employeeSkillRepository, EmployeeRepository employeeRepository) {
        this.iAllocationService = iAllocationService;
        this.iRequirementProjectSkillService = iRequirementProjectSkillService;
        this.iRequirementProjectService = iRequirementProjectService;
        this.employeeRoleRepository = employeeRoleRepository;
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();
    Random random = new Random();

    @GetMapping()
    public ResponseEntity<Object> getAlls() {
        List<Allocation> data = null;
        try {
            data = iAllocationService.getAlls();
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
        Allocation data = iAllocationService.getById(id);
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

    @GetMapping("/skill/{combination_skill_id}")
    public ResponseEntity<Object> getByCombinationSkill(@PathVariable("combination_skill_id") Integer combinationSkillId) {
        List<Allocation> data = iAllocationService.getByCombinationSkill(combinationSkillId);
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

    @GetMapping("/project/{project_id}")
    public ResponseEntity<Object> getAllByProjectId(@PathVariable("project_id") Integer projectId) {
        List<Allocation> data = iAllocationService.getAllByProjectId(projectId);
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

    @PostMapping("/employee/{combination_skill_id}")
    public ResponseEntity<Object> create(@PathVariable("combination_skill_id") Integer combinationSkillId, @RequestBody Allocation request) {
        RequirementProjectSkill requirementProjectSkill = iRequirementProjectSkillService.getRequirementProjectSkillByCombinationSkillId(combinationSkillId);
        Allocation allocation = new Allocation();
        Integer skillId;
        Integer levelIdS;
        Integer requirementProjectId;
        Integer roleId;
        Integer levelIdR;
        List<Employee> employees;
        Employee chose;
        Integer id;
        if(requirementProjectSkill != null) {
            skillId = requirementProjectSkill.getSkillId();
            levelIdS = requirementProjectSkill.getLevelId();
            requirementProjectId = requirementProjectSkill.getRequirementProjectId(); // cái này dùng để lấy role vs level
            RequirementProject requirementProject = iRequirementProjectService.getRequirementProjectById(requirementProjectId);
            if(requirementProject != null) {
                roleId = requirementProject.getRoleId();
                levelIdR = requirementProject.getLevelId();
                employees = employeeRepository.getEmployee(roleId, levelIdR, skillId, levelIdS);
                if(employees.size() > 0) {
                    chose = employees.get(random.nextInt(employees.size()));
                    id = chose.getId();
                    allocation.setEmployeeId(id);
                    allocation.setCombinationSkillId(combinationSkillId);
                    allocation.setJoiningDate(request.getJoiningDate());
                    allocation.setFinishDate(request.getFinishDate());
                    try {
                        iAllocationService.create(allocation);
                    } catch (Exception e) {
                        meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                        meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                        response.put("meta", meta);
                        response.put("data", null);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    meta.setCode(HttpStatus.CREATED.value());
                    meta.setMessage(HttpStatus.CREATED.getReasonPhrase());
                    response.put("meta", meta);
                    response.put("data", allocation);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    logger.error("is not valid!");
                    meta.setCode(HttpStatus.NOT_FOUND.value());
                    meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                    response.put("meta", meta);
                    response.put("data", null);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                logger.error("is not valid!");
                meta.setCode(HttpStatus.NOT_FOUND.value());
                meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.put("meta", meta);
                response.put("data", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } else {
            logger.error("is not valid!");
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody Allocation request) {
        Allocation allocation = iAllocationService.getById(id);
        if(allocation != null ) {
            allocation.setJoiningDate(request.getJoiningDate() != null ? request.getJoiningDate() : allocation.getJoiningDate());
            allocation.setJoiningDate(request.getFinishDate() != null ? request.getFinishDate() : allocation.getFinishDate());
            allocation.setWorkLoad(request.getWorkLoad() != null ? request.getWorkLoad() : allocation.getWorkLoad());
            request = iAllocationService.update(allocation);
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        try {
            iAllocationService.deleteById(id);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("error when delete allocation" + e);
            meta.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            meta.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllRequirementProjects() {
        iAllocationService.deleteAlls();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

//hello
//hello2
