package com.example.vssemployee.controller;

import com.example.vssemployee.entity.Skill;
import com.example.vssemployee.response.Meta;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.vssemployee.service.ISkillService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manhdt14
 * created in 8/30/2021 8:46 PM
 */

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/skills")
public class SkillController {
    private final static Logger logger = Logger.getLogger(SkillController.class);
    private ISkillService iSkillService;

    public SkillController(ISkillService iSkillService) {
        this.iSkillService = iSkillService;
    }
    Map<String, Object> response = new HashMap<>();
    Meta meta = new Meta();

    /**
     * get all skills
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> getAllSKill() {
        List<Skill> data = null;
        try {
            data = iSkillService.getAllSkills();
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
     * get skill by id
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getSkillById(@PathVariable("id") Integer id) {
        Skill data = iSkillService.getSkillById(id);
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
     * get skill by name
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getSkillByName(@PathVariable("name") String name) {
        List<Skill> data = iSkillService.getSkillByName(name);
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
     * create a skill
     * @param skill
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> addSkill(@RequestBody Skill skill) {
        Skill data = iSkillService.created(skill);
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
//    public Skill addskill(@RequestBody Skill skill){
//        Skill data = iSkillService.created(skill);
//        return data;
//    }

    /**
     * update name of skill by id
     * @param id
     * @param skillRsquest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSkill(@PathVariable("id") Integer id, @RequestBody Skill skillRsquest) {
        Skill skill = iSkillService.getSkillById(id);
        if(skill != null) {
            skill.setName(skillRsquest.getName());
            skillRsquest = iSkillService.update(skill);
            meta.setCode(HttpStatus.OK.value());
            meta.setMessage(HttpStatus.OK.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", skillRsquest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            meta.setCode(HttpStatus.NOT_FOUND.value());
            meta.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
            response.put("meta", meta);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
//    public Skill putskill(@PathVariable(name = "id") Integer id, @RequestBody Skill skillRsquest){
//        Skill skill = iSkillService.getSkillById(id);
//        skill.setName(skillRsquest.getName());
//        skillRsquest = iSkillService.update(skill);
//        return skillRsquest;
//    }

    /**
     * delete a skill by id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSkill(@PathVariable("id") Integer id) {
        try {
            iSkillService.deleteById(id);
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

    /**
     * delete all skills
     * @return
     */
    @DeleteMapping()
    public ResponseEntity<Object> deleteAllSkills() {
        iSkillService.deleteAll();
        meta.setCode(HttpStatus.OK.value());
        meta.setMessage(HttpStatus.OK.getReasonPhrase());
        response.put("meta", meta);
        response.put("data", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
