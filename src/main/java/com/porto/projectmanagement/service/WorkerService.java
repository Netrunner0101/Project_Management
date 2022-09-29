package com.porto.projectmanagement.service;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.repository.SkillRepository;
import com.porto.projectmanagement.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepo;
    @Autowired
    private SkillRepository skillRepo;

    public List<Worker> getAllWorker(){
        log.info("All worker");
        return workerRepo.findAll();
    }

    public Optional<Worker> getWorkerById(int id){
        log.info("Worker BY ID:{}",id);
        return workerRepo.findById(id);
    }

    public Worker newWorker(Worker worker){
        log.info("New worker create :{}",worker);
        return workerRepo.save(worker);
    }

    public Worker updateWorker(Worker worker,int id){
        log.info("Update id:{} worker :{}",id,worker);
        Worker workerUpdate =(Worker) workerRepo.findById(id).get();
        workerUpdate.setFirstname(worker.getFirstname());
        workerUpdate.setLastname(worker.getLastname());
        workerUpdate.setEmail(worker.getEmail());
        return workerRepo.save(workerUpdate);
    }

    public void addSkillToWorker(String firstname, String skillName){
        log.info("Skill:{} added to worker :{}",skillName,firstname);
        Worker worker = workerRepo.findByFirstname(firstname);
        Skill skill = skillRepo.findBySkillName(skillName);
        // Need to think in reverse
        skill.setWorker(worker);
    }

    public void deleteWorker(int id){
        log.info("Delete worker id:{}",id);
        workerRepo.deleteById(id);
    }

}
