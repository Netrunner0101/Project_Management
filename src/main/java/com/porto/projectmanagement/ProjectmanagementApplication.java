package com.porto.projectmanagement;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.service.SkillService;
import com.porto.projectmanagement.service.WorkerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;

@SpringBootApplication
public class ProjectmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectmanagementApplication.class, args);
    }

    //Don't forget to anotation @Bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*@Bean
    CommandLineRunner run (WorkerService workerService, SkillService skillService){
        return args -> {
            workerService.newWorker(new Worker(1,"eric","eric","eric@gmail.com",new ArrayList<>(),new ArrayList<>()));
            workerService.newWorker(new Worker(2,"buzz","lightning","buzz@gmail.com",new ArrayList<>(),new ArrayList<>()));
            workerService.newWorker(new Worker(3,"jack","samurai","jack@gmail.com",new ArrayList<>(),new ArrayList<>()));

            skillService.newSkill(new Skill(1,"JAVA","Good",new Worker()));
            skillService.newSkill(new Skill(2,"C","Excellent",new Worker()));
            skillService.newSkill(new Skill(3,"Rust","Very good",new Worker()));

            workerService.addSkillToWorker("eric","JAVA");
        };
    }*/

}
