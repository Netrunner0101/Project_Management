package com.porto.projectmanagement.repository;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SkillRepository extends JpaRepository<Skill,Integer> {
    @Query(value = "select * from skill s where s.skill_name = :skillname",
            nativeQuery = true)
    Skill findBySkillName(@Param("skillname")String skillname);
}
