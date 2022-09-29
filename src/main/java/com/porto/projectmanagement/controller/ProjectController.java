package com.porto.projectmanagement.controller;

import com.porto.projectmanagement.model.Project;
import com.porto.projectmanagement.service.ProjectService;
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
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(){
        return ResponseEntity.ok().body(projectService.getAllProject());
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Optional<Project>> getProjectsById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(projectService.getProjectById(id));
    }

    @PostMapping("/project")
    public ResponseEntity<Project> newProject(@RequestBody Project project){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/project").toUriString());
        return ResponseEntity.created(uri).body(projectService.newProject(project));
    }

    @PostMapping("/project/{projectname}/addWorker/{workername}")
    public void addWorkerToProject(@PathVariable("projectname") String projectName,@PathVariable("workername") String workerName){
        projectService.addWorkerToProject(workerName,projectName);
    }

    @PutMapping ("/project/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project,@PathVariable("id") int id){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/project/:id").toUriString());
        return ResponseEntity.created(uri).body(projectService.updateProject(project,id));
    }

    @DeleteMapping("/project/{id}")
    public void deleteProjectsById(@PathVariable("id") int id){
        projectService.deleteProject(id);
    }

}
