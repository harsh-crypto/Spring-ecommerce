package com.shopme.WebParent.BackEnd.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repo;
	
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin","Manage Everything");
		Role savedRole = repo.save(roleAdmin);
		assertThat(savedRole.getID()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRoles() {
		Role roleSalesPerson = new Role("SalesPerson","Manage product price customer, shipping, and salesreport");
		Role roleEditor = new Role("Editor","Manage categories, brands, products, articles and menus");
		Role roleShipper = new Role("Shipper","View products and view orders and update status");
		Role roleAdmin = new Role("Admin","God MOde");
		Role roleAssistant = new Role("Assistant"," managae reviews and questions");
		
		repo.saveAll(List.of(roleSalesPerson,roleEditor,roleShipper,roleAssistant));
	}
	
}
