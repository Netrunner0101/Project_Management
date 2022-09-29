package com.porto.projectmanagement.service.security;

import com.porto.projectmanagement.model.security.RoleEntity;
import com.porto.projectmanagement.model.security.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserService {

    UserModel saveUser(UserModel userModel);

    RoleEntity saveRole(RoleEntity roleEntity);

    void addRoleToUser(String userName,String roleName);

    UserModel getUser(String userName);

    List<UserModel> getUsers();

}
