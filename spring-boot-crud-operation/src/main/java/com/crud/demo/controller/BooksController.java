package com.crud.demo.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.dto.BaseResponse;
import com.crud.demo.dto.Response;
import com.crud.demo.dto.SomeObject;
import com.crud.demo.model.Books;
import com.crud.demo.service.BooksService;

//mark class as Controller
@RestController
public class BooksController {
//autowire the BooksService class
	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	BooksService booksService;

//creating a get mapping that retrieves all the books detail from the database 
	@GetMapping("/book")
	private List<Books> getAllBooks() {
		return booksService.getAllBooks();
	}

//creating a get mapping that retrieves the detail of a specific book
	@GetMapping("/book/{bookid}")
	private Books getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}

//creating a delete mapping that deletes a specified book
	@DeleteMapping("/book/{bookid}")
	private void deleteBook(@PathVariable("bookid") int bookid) {
		booksService.delete(bookid);
	}

//creating post mapping that post the book detail in the database
	@PostMapping(value = "/books")
	private BaseResponse saveBook(@RequestBody Books books) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			booksService.saveOrUpdate(books);
			baseResponse.setStatusCode(0);
			baseResponse.setStatusMessage("success");
		} catch (Exception e) {
			logger.info("Exception while saving book : " + e);
			baseResponse.setStatusCode(1);
			baseResponse.setStatusMessage("failure");
		}
		return baseResponse;
	}

//returning xml response
//creating put mapping that updates the book detail 
	@PutMapping(value= "/books", produces = MediaType.APPLICATION_XML_VALUE)
	private Response update(@RequestBody Books books) {
		Response response = new Response();
		booksService.saveOrUpdate(books);
		response.setStatusMessage("success");
		return response;
	}
	
	@RequestMapping(value = "/mylink", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	//set response status
	@ResponseStatus(HttpStatus.BAD_REQUEST )
	public Response doIt(){
	    return new Response();
	}

	
}
