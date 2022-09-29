package com.porto.projectmanagement.dataloader;

import com.porto.projectmanagement.model.Project;
import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.model.security.RoleEntity;
import com.porto.projectmanagement.model.security.UserModel;
import com.porto.projectmanagement.repository.ProjectRepository;
import com.porto.projectmanagement.repository.SkillRepository;
import com.porto.projectmanagement.repository.WorkerRepository;
import com.porto.projectmanagement.service.ProjectService;
import com.porto.projectmanagement.service.SkillService;
import com.porto.projectmanagement.service.WorkerService;
import com.porto.projectmanagement.service.security.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class dataloader implements CommandLineRunner {

    private final WorkerRepository workerRepository;

    private final SkillRepository skillRepository;

    private final ProjectRepository projectRepo;
    private final WorkerRepository workerRepo;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private SkillService skillService;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserServiceImpl userService;

    public void loadData(){

        workerService.newWorker(new Worker(1,"eric","lung","eric@gmail.com",new ArrayList<Skill>(),new ArrayList<>()));
        workerService.newWorker(new Worker(2,"johny","silverhand","silverhand@gmail.com",new ArrayList<Skill>(),new ArrayList<>()));
        workerService.newWorker(new Worker(3,"jack","samurai","jack@gmail.com",new ArrayList<Skill>(),new ArrayList<>()));

        skillService.newSkill(new Skill(1,"JAVA","good",null));
        skillService.newSkill(new Skill(2,"C#","Very good",null));
        skillService.newSkill(new Skill(3,"Angular","Medium",null));

        projectRepo.save(
                Project.builder()
                        .projectName("Assurance")
                        .client("Ethias")
                        .date_start(new Date(2022,07,15))
                        .date_end(new Date(2022,12,15))
                        .build()
        );

        projectRepo.save(
                Project.builder()
                        .projectName("banking")
                        .client("ING")
                        .date_start(new Date(2022,07,15))
                        .date_end(new Date(2022,012,15))
                        .build()
        );

        userService.saveUser(new UserModel(1,"eric","eric@gmail.com","eric",new ArrayList<>()));
        userService.saveUser(new UserModel(2,"buzz","buzz@gmail.com","buzz",new ArrayList<>()));
        userService.saveUser(new UserModel(3,"jack","jack@gmail.com","jack",new ArrayList<>()));

        userService.saveRole(new RoleEntity(1,"ROLE_USER",new ArrayList<>()));
        userService.saveRole(new RoleEntity(2,"ROLE_ADMIN",new ArrayList<>()));
        userService.saveRole(new RoleEntity(3,"ROLE_PM",new ArrayList<>()));

        userService.addRoleToUser("eric","ROLE_USER");
        userService.addRoleToUser("eric","ROLE_ADMIN");
        userService.addRoleToUser("eric","ROLE_PM");
        userService.addRoleToUser("buzz","ROLE_USER");
        userService.addRoleToUser("jack","ROLE_PM");
        userService.addRoleToUser("jack","ROLE_USER");

        projectService.addWorkerToProject("eric","Assurance");
        projectService.addWorkerToProject("jack","Assurance");
        projectService.addWorkerToProject("johny","banking");

        workerService.addSkillToWorker("jack","JAVA");
        workerService.addSkillToWorker("jack","C#");
        workerService.addSkillToWorker("eric","Angular");
        workerService.addSkillToWorker("eric","JAVA");

        // Don't Forget to add @Builder in the model
        /*workerRepository.save(Worker.builder()
                .firstname("Eric")
                .lastname("Lung")
                .email("eric@gmail.com")
                .build());

        workerRepository.save(Worker.builder()
                .firstname("Jack")
                .lastname("Samurai")
                .email("jack@gmail.com")
                .build());

        workerRepository.save(Worker.builder()
                .firstname("buzz")
                .lastname("lightyear")
                .email("buzz@gmail.com")
                .build());

        skillRepository.save(
                Skill.builder()
                .skillName("JAVA")
                .knowledge("Excellent")
                .build());

        skillRepository.save(
                Skill.builder()
                        .skillName("C#")
                        .knowledge("Good")
                        .build());
*/


    }

    @Override
    public void run(String... args) throws Exception {
        if(workerRepository.count() ==0 && skillRepository.count()==0 && projectRepo.count()==0){
            loadData();
        }
    }
}
