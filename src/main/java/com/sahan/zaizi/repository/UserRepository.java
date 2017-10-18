package com.sahan.zaizi.repository;

import org.springframework.data.repository.CrudRepository;

import com.sahan.zaizi.domain.User;

/**
 * Created by sahan on 4/8/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {

//	@Query(value = "select count(e)>0 from User e where e.name = ?1 and e.pwd = ?2 ", nativeQuery = true)
//	public boolean isUserExist(String userName, String pwd);

}
