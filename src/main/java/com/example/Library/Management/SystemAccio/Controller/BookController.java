package com.example.Library.Management.SystemAccio.Controller;

import com.example.Library.Management.SystemAccio.Models.Book;
import com.example.Library.Management.SystemAccio.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/AddBook")
    public String Addbook(@RequestBody Book  book, @RequestParam("AuthorId") Integer authorID){
       String res= bookService.addbook(book,authorID);
       return res;

    }


}
