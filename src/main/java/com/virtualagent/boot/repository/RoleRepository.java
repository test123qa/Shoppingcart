package com.virtualagent.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtualagent.boot.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
