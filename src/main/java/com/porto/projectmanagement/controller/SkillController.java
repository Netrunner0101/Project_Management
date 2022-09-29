package com.porto.projectmanagement.controller;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.service.SkillService;
import com.porto.projectmanagement.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SkillController {

    private final SkillService skillService;

    @GetMapping("/skills")
    public ResponseEntity<List<Skill>> getAllSkills(){
        return ResponseEntity.ok().body(skillService.getAllSkill());
    }

    @GetMapping("/skill/{id}")
    public ResponseEntity<Optional<Skill>> getSkillById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(skillService.getSkillById(id));
    }

    @PostMapping("/skill")
    public ResponseEntity<Skill> newSkill(@RequestBody Skill skill){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/skill").toUriString());
        return ResponseEntity.created(uri).body(skillService.newSkill(skill));
    }

    @PutMapping ("/skill/{id}")
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill,@PathVariable("id") int id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/skill/:id").toUriString());
        return ResponseEntity.created(uri).body(skillService.updateSkill(skill,id));
    }

    @DeleteMapping("/skill/{id}")
    public void deleteSkillById(@PathVariable("id") int id){
        skillService.deleteSkill(id);
    }

}
