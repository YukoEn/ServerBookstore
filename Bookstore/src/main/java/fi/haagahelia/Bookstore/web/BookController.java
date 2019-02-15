package fi.haagahelia.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.Bookstore.domain.Category;
import fi.haagahelia.Bookstore.domain.CategoryRepository;
import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
	// RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) repository.findAll();
    }
    
	// RESTful service to get a book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long bookId) {	
    	return repository.findById(bookId);
    } 
    
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    
   @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) { 
    	model.addAttribute("bookFound", repository.findById(bookId).get());
    	model.addAttribute("categories", crepository.findAll());  	
    	return "editbook"; 
    }
    
    //@RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.POST})   
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public String update(Book book, Model model){
    	Book bookFromDb = repository.findById(book.getId()).get();
        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setAuthor(book.getAuthor());
        bookFromDb.setYear(book.getYear());
        bookFromDb.setIsbn(book.getIsbn());               
        Category categoryTemp = book.getCategory();
        Long catId = categoryTemp.getCategoryid();
        Category categoryEd = crepository.findById(catId).get();       
        bookFromDb.setCategory(categoryEd);  
    	repository.save(bookFromDb);
    	return "redirect:booklist";
		 	
    	
    	//model.addAttribute("ID", book.getId());  
    	//model.addAttribute("Title", book.getTitle());	
		//model.addAttribute("Author", book.getAuthor());	
		//model.addAttribute("Isbn", book.getIsbn());
		//model.addAttribute("Price", book.getPrice());
		//model.addAttribute("Category", categoryEd);
		//model.addAttribute("CategoryId", catId);
        //return "message";  	
    	
    	
    }
		
}
