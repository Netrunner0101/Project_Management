package com.porto.projectmanagement.service.security;

import com.porto.projectmanagement.model.security.RoleEntity;
import com.porto.projectmanagement.model.security.UserModel;
import com.porto.projectmanagement.repository.security.RoleRepository;
import com.porto.projectmanagement.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final RoleRepository roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepo.findByUserName(username);
        if(userModel==null){
            log.error("UserModel not found");
            throw new UsernameNotFoundException("User not found");
        }else {
            log.info("User found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userModel.getRoles().forEach(roleEntity -> {
            authorities.add(new SimpleGrantedAuthority(roleEntity.getRoleName()));
        });
        return new User(userModel.getEmail(),userModel.getPassword(),authorities);
    }

    @Override
    public UserModel saveUser(UserModel userModel) {
        log.info("New user save : {}",userModel);
        userModel.setPassword(bCryptPasswordEncoder.encode(userModel.getPassword()));
        return userRepo.save(userModel);
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        log.info("New user save : {}",roleEntity);
        return roleRepo.save(roleEntity);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("New role {} added to {}",roleName,userName);
        UserModel userM = userRepo.findByUserName(userName);
        RoleEntity roleM= roleRepo.findByRoleName(roleName);
        userM.getRoles().add(roleM);
    }

    @Override
    public UserModel getUser(String userName) {
        log.info("Get user : {}",userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<UserModel> getUsers() {
        return userRepo.findAll();
    }

}
