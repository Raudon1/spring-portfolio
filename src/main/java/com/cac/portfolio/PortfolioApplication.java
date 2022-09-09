package com.cac.portfolio;

import com.cac.portfolio.domain.*;
import com.cac.portfolio.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@SpringBootApplication
@ComponentScan("com.cac") //Con esta linea los test funcionan/With this line the test work
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private java.sql.Date parseDate(String date) {
		try {
			return new Date(DATE_FORMAT.parse(date).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	private java.sql.Timestamp parseTimestamp(String timestamp) {
		try {
			return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Bean
	CommandLineRunner run(UserService userService, ShopService shopService, DeliveryManService deliveryManService, CatalogueService catalogueService, ClientService clientService, CategoryService categoryService, ProductsService productsService, CustomerOrderService customerOrderService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			shopService.saveShop(new Shop(null,"test1","test1","test1",new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
			deliveryManService.saveDelivery(new DeliveryMan(null, "testDeliver","testDeliver","45456",new ArrayList<>()));
			catalogueService.saveCatalogue(new Catalogue(null,"testCatalogue",parseDate("2022-09-29"),new ArrayList<>()));
			clientService.saveClient(new Client(null, "testClient","sad","12","as@s",new ArrayList<>()));
			categoryService.saveCategory(new Category(null,"testCategory",new ArrayList<>()));
			productsService.saveProducts(new Products(null,"testProduct","testBrand",4f,4f));

			customerOrderService.saveOrder(new CustomerOrder(null,"1234",true,parseTimestamp("2022-07-31 12:30:00"),parseTimestamp("2022-07-31 12:30:00"), new ArrayList<>()));

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
			customerOrderService.addProductsToOrder("testProduct","1234");
			clientService.addOrderToClient("1234","testClient");
			deliveryManService.addOrderToDeli("1234","testDeliver");
		};
	}
}

