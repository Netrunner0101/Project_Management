package com.porto.projectmanagement.repository.security;

import com.porto.projectmanagement.model.security.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    UserModel findByEmail(String email);

    UserModel findByUserName(String userName);

}
