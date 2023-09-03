package com.example.practice;

import com.example.practice.domain.User;
import com.example.practice.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class PracticeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

	@Autowired
	UserDao userDao;

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setName("test2");
//		user.setEmail("test2@test.com");
//		user.setPassword("test123");
//		user.setRegdate(LocalDateTime.now());
//		userDao.addUser(user);

//		User user = userDao.getUser(2);
//		System.out.println(user.getUserId()+","+user.getName()+","+user.getEmail()+","+user.getPassword()+","+user.getRegdate());

//		boolean flag = userDao.deleteUser(3);
//		System.out.println("flag : " + flag);


		List<User> list = userDao.getUsers();
		for(User user : list){
			System.out.println(user.getUserId()+","+user.getName()+","+user.getEmail()+","+user.getPassword()+","+user.getRegdate());
		}
	}

}
