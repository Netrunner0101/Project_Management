package com.porto.projectmanagement.controller;

import com.porto.projectmanagement.model.Project;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.service.ProjectService;
import com.porto.projectmanagement.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private ProjectService projectService;

    @GetMapping("/workers")
    public List<Worker> getAllWorkers(){
        return workerService.getAllWorker();
    }
    @GetMapping("/worker/{id}")
    public ResponseEntity<Optional<Worker>> getWorkerById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(workerService.getWorkerById(id));
    }

    @GetMapping("/worker/projects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok().body(projectService.getAllProject());
    }

    @GetMapping("/worker/project/{id}")
    public ResponseEntity<Optional<Project>> getProjectsById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }

    @PostMapping("/worker")
    public ResponseEntity<Worker> newWorker(@RequestBody Worker worker){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/worker").toUriString());
        return ResponseEntity.created(uri).body(workerService.newWorker(worker));
    }

    @PostMapping("/worker/{firstrname}/addSkill/{skillname}")
    public void addSkillToWorker(@PathVariable("firstname") String firstname,@PathVariable("skillname") String skillname){
        workerService.addSkillToWorker(firstname,skillname);
    }

    @PutMapping ("/worker/{id}")
    public ResponseEntity<Worker> updateWorker(@RequestBody Worker worker,@PathVariable("id") int id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/worker/:id").toUriString());
        return ResponseEntity.created(uri).body(workerService.updateWorker(worker,id));
    }

    @DeleteMapping("/worker/{id}")
    public void deleteProjectsById(@PathVariable("id") int id){
        workerService.deleteWorker(id);
    }
}
