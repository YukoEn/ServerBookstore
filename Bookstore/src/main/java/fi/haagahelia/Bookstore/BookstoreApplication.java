package fi.haagahelia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.domain.User;
import fi.haagahelia.Bookstore.domain.UserRepository;
import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Fairytale"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Health"));
			
			brepository.save(new Book("A Farewell to Arms", "Ernest Hemmingway", "1929", "1232323-21", 25.0, crepository.findByName("Romance").get(0)));
			brepository.save(new Book("Animal Farm", "George Orwell", "1945", "2212343-5", 55.0, crepository.findByName("Fairytale").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$iuHLjbnHPU2a1hRDhscvce/GPufl2efa0Nqdr5w76jvwCx29X4/e6", "user@mail.com", "USER");
			//password
			//User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$dAjYYmC0ptnSqaSUlMz3XO8i2c5JSd5nAG/3Qxn.UnjoFr4WBqghK", "admin@mail.fi", "ADMIN");
			// password2
			//User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);			
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

