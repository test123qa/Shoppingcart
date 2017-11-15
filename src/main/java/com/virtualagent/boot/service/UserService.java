package com.virtualagent.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtualagent.boot.domain.User;
import com.virtualagent.boot.repository.UserRepository;
import com.virtualagent.boot.util.ShoppingCartUtil;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sahan on 4/9/2016.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ShoppingCartUtil shoppingCartUtil;

    private static List<User> users = new ArrayList<>();

    /*static {
        users.add(new User("user1@gmail.com","user1", "admin"));
        users.add(new User("user2@gmail.com","user2", "root"));
    }

    public void saveInitialBatch(){
        userRepository.save(users);
    }*/
    
    public User saveUserDetails(String ip, String userId, HttpServletRequest request){
    	System.out.println("In UserService....saveUserDetails() method....");
    	String userGeoLocation = shoppingCartUtil.getIPInfo(ip);
    	System.out.println(userGeoLocation);
    	User user = new User();
    	user.setUserType("guest");
    	user.setTempUserId(userId);
    	user.setGeoLocation(userGeoLocation);
    	User u = userRepository.save(user);
    	return u;
    }
    
    public User findByUserName(String userName){
    	System.out.println("In UserService....saveUserDetails() method....");
    	return userRepository.findByUserName(userName);
    }
    
    public User findByTokenId(String tokenId){
    	System.out.println("In UserService....saveUserDetails() method....");
    	return userRepository.findByCookieTokenId(tokenId);
    }

}
