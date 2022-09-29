package com.porto.projectmanagement.repository;

import com.porto.projectmanagement.model.Skill;
import com.porto.projectmanagement.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface WorkerRepository extends JpaRepository<Worker,Integer> {

   @Query(value = "select * from worker w where w.firstname=:firstname",
           nativeQuery = true)
   Worker findByFirstname(@Param("firstname")String firstname);
}

