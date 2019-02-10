package fi.haagahelia.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Fairytale"));
			crepository.save(new Category("History"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Health"));
			
			brepository.save(new Book("A Farewell to Arms", "Ernest Hemmingway", "1929", "1232323-21", 25.0, crepository.findByName("Romance").get(0)));
			brepository.save(new Book("Animal Farm", "George Orwell", "1945", "2212343-5", 55.0, crepository.findByName("Fairytale").get(0)));
									
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}

