package com.shopme.WebParent.BackEnd.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		Role roleAdmin = entityManager.find(Role.class,1);
		User userHarsh = new User("harshmalik8@gmail.com", "Harsh"," Malik","Harsh2020");
		userHarsh.add_role(roleAdmin);
		User savedUser = repo.save(userHarsh);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateUserwithTwoRoles() {
		User userRavi = new User("ravi@gmail.com","Ravi","Kkumar","ravi2023");
		Role roleEditor = new Role(2);
		Role roleAssisstant = new Role(4);
		
		userRavi.add_role(roleEditor);
		userRavi.add_role(roleAssisstant);
		
		User savedUser = repo.save(userRavi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void TestListAllTheUser() {
		Iterable<User> allUser = repo.findAll();
		allUser.forEach(user->System.out.print(user.toString()));
	}
	
	@Test
	public void TestGetUSerFromId() {
		User user=repo.findById(1).get();
		assertThat(user).isInstanceOf(User.class);
		assertThat(user).isNotNull();
	}
	
	@Test
	public void TestUpdatingUser() {
		User user=repo.findById(1).get();
		user.setFirstName("Kiki");
		User savedUser = repo.save(user);
		assertThat(savedUser.getId()).isGreaterThan(0);
		assertThat(savedUser.getFirstName()).isEqualTo("Kiki");
	}
	@Test
	public void updatingActive() {
		User user = repo.findById(1).get();
		user.setEnabled(true);
		User savedUser = repo.save(user);
		assertThat(savedUser.isEnabled()).isEqualTo(true);
	}
	@Test
	public void TestUpdatingUserRole() {
		User user = repo.findById(1).get();
		Role roleSales = new Role(1);
		Role roleAssist =new Role(4);	
		user.getRoles().remove(roleSales);
		user.add_role(roleAssist);
		repo.save(user);
		
	}
	
	@Test
	public void DeleteUser() {
		repo.deleteById(1);
	}
}
