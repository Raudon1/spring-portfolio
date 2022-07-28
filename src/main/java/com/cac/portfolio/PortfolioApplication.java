package com.cac.portfolio;

import com.cac.portfolio.domain.*;
import com.cac.portfolio.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, ShopService shopService, DeliveryManService deliveryManService, CatalogueService catalogueService, ClientService clientService, CategoryService categoryService, ProductsService productsService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			shopService.saveShop(new Shop(null,"test1","test1","test1",new ArrayList<Client>(), new ArrayList<Catalogue>(), new ArrayList<DeliveryMan>()));
			deliveryManService.saveDelivery(new DeliveryMan(null, "testDeliver","testDeliver","45456")); //,new ArrayList<Order>()
			catalogueService.saveCatalogue(new Catalogue(null,"testCatalogue",15,new ArrayList<>()));
			clientService.saveClient(new Client(null, "testClient","sad","12","as@s")); //,new ArrayList<Order>()
			categoryService.saveCategory(new Category(null,"testCategory",new ArrayList<>()));
			productsService.saveProducts(new Products(null,"testProduct","testBrand",4f,4f));

			userService.saveUser(new User(null, "John Travolta", "john","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Will Smith", "will","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Jim Carry", "jim","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "Arnold Schwarzenegger", "arnold","1234",new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");
			shopService.addCatalogueToShop("testCatalogue","test1");
			shopService.addDeliverMenToShop("testDeliver","test1");
			shopService.addClientToShop("testClient","test1");
			catalogueService.addCategoryToCatalogue("testCategory","testCatalogue");
			categoryService.addProductToCategory("testProduct","testCategory");
		};
	}
}

