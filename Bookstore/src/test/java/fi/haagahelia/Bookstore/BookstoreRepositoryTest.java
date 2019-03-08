package fi.haagahelia.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;
import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.domain.User;
import fi.haagahelia.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

    @Autowired
    private BookRepository brepository;
    @Autowired
    private CategoryRepository crepository;
    @Autowired
    private UserRepository urepository;
	
    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = brepository.findByTitle("Animal Farm");        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", "2014", "1607747308", 20.0, new Category("Life style"));
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
      
    @Test
    public void deleteBook() {
        List<Book> books = brepository.findByTitle("Animal Farm");        
    	brepository.deleteById(books.get(0).getId());
    	assertThat(brepository.findByTitle("Animal Farm")).isEmpty();
    }
    
    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories  = crepository.findByName("Romance");        
        assertThat(categories).hasSize(1);
    	assertThat(categories.get(0).getCategoryid()).isNotNull();
    }
    
    @Test
    public void createNewCategory() {
    	Category category = new Category("Hobby");
    	crepository.save(category);
    	assertThat(category.getCategoryid()).isNotNull();
    }
      
    @Test
    public void deleteCategory() {
        List<Category> categories = crepository.findByName("Health");        
    	crepository.deleteById(categories.get(0).getCategoryid());
    	assertThat(crepository.findByName("Health")).isEmpty();
    }
    @Test
    public void findByUsernameShouldReturnUser() {
        User user  = urepository.findByUsername("user");        
    	assertThat(user.getId()).isNotNull();
    }
    
    @Test
    public void createNewUser() {
    	User user = new User("user A", "$2a$10$nBVQK1j1QZoj75sCkoI9ce2DbQNJIWo0jmyEzOIrrv8Y.8BOA6PBi", "userA@yahoo.com", "USER");
    	// passwordA
    	urepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }
      
    @Test
    public void deleteUser() {
        User user = urepository.findByUsername("user");        
    	urepository.deleteById(user.getId());
    	assertThat(urepository.findByUsername("user")).isNull();
    }
    
}