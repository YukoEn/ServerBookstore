package fi.haagahelia.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.Bookstore.domain.Book;
import fi.haagahelia.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
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
    	return "editbook"; 
    }
    
    //@RequestMapping(value = "/update/{id}", method = {RequestMethod.GET, RequestMethod.POST})   
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public String update(Book bookFound, Model model){
    	Book bookFromDb = repository.findById(bookFound.getId()).get();
        bookFromDb.setTitle(bookFound.getTitle());
        bookFromDb.setAuthor(bookFound.getAuthor());
        bookFromDb.setYear(bookFound.getYear());
        bookFromDb.setIsbn(bookFound.getIsbn());         	
    	repository.save(bookFromDb);
    	return "redirect:booklist";
		 	
    	/*
    	model.addAttribute("ID", bookFound.getId());  
    	model.addAttribute("Title", bookFound.getTitle());	
		model.addAttribute("Author", bookFound.getAuthor());	
		model.addAttribute("Isbn", bookFound.getIsbn());
		model.addAttribute("Price", bookFound.getPrice());
        return "message";  	
    	 */
    	
    }
		
}
