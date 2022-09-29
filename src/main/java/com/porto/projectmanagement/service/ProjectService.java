package com.porto.projectmanagement.service;

import com.porto.projectmanagement.model.Project;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.repository.ProjectRepository;
import com.porto.projectmanagement.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectService {
    /**
     * Don't forget to Autowired and no static
     * */
    @Autowired
    private ProjectRepository projectRepo;
    @Autowired
    private WorkerRepository workerRepo;

    public List<Project> getAllProject(){
        log.info("All projects");
        return projectRepo.findAll();
    }

    public Optional<Project> getProjectById(int id){
        log.info("Project Id: {}",id);
        return projectRepo.findById(id);
    }

    public Project newProject(Project project){
        log.info("New project create :{}",project);
        return projectRepo.save(project);
    }

    public Project updateProject(Project project,int id){
        log.info("Update id:{} project :{}",id,project);
        Project projectUpdate = projectRepo.findById(id).get();
        projectUpdate.setProjectName(project.getProjectName());
        projectUpdate.setClient(project.getProjectName());
        projectUpdate.setDate_start(project.getDate_start());
        projectUpdate.setDate_end(project.getDate_end());
        return projectRepo.save(projectUpdate);
    }

    public void addWorkerToProject(String firstname, String projectName){
        log.info("Worker:{} added to project :{}",firstname, projectName);
        Worker worker = workerRepo.findByFirstname(firstname);
        Project project = projectRepo.findByProjectName(projectName);
        project.getWorkers().add(worker);
    }

    public void deleteProject(int id){
        log.info("Delete project id:{}",id);
        projectRepo.deleteById(id);
    }
}
