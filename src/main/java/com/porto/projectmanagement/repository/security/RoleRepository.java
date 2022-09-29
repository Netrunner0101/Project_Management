package com.porto.projectmanagement.repository.security;

import com.porto.projectmanagement.model.security.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    RoleEntity findByRoleName(String roleName);
}
