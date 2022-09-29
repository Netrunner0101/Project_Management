package com.porto.projectmanagement.service;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import com.porto.projectmanagement.repository.SkillRepository;
import com.porto.projectmanagement.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SkillService {

    @Autowired
    private SkillRepository skillRepo;

    @Autowired
    private WorkerRepository workerRepo;

    public List<Skill> getAllSkill(){
        log.info("All Skills");
        return skillRepo.findAll();
    }

    public Optional<Skill> getSkillById(int id){
        log.info("Worker BY ID:{}",id);
        return skillRepo.findById(id);
    }

    public Skill newSkill(Skill skill){
        log.info("New skill create :{}",skill);
        return skillRepo.save(skill);
    }

    public Skill updateSkill(Skill skill,int id){
        log.info("Update id:{} skill :{}",id,skill);
        Skill skillUpdate =(Skill) skillRepo.findById(id).get();
        skillUpdate.setSkillName(skill.getSkillName());
        skillUpdate.setKnowledge(skill.getKnowledge());
        return skillRepo.save(skillUpdate);
    }

    public void deleteSkill(int id){
        log.info("Delete skill id:{}",id);
        skillRepo.deleteById(id);
    }
}
