package fi.haagahelia.Bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.Bookstore.domain.Book;

@Controller
@ResponseBody

public class BookController {

	@RequestMapping("/index")
	public String index() {
		return "Index";
	}
		
}
