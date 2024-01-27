package com.example.Library.Management.SystemAccio.Controller;

import com.example.Library.Management.SystemAccio.Models.Author;
import com.example.Library.Management.SystemAccio.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Author")

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/getAllBooksByAuthor")
    public List<String> GetAllBook(@RequestParam("Authorname") String Authorname){
        return authorService.GetAllBookk(Authorname);

    }
    @GetMapping("/getAllBooksByAuthor")
    public List<String> GetAllBook(@RequestParam("AuthorID") Integer AuthorID) throws Exception {
        return authorService.GetAllBook(AuthorID);

    }

    @PostMapping("/AddAuthor")
    public ResponseEntity AddAuthor(@RequestBody Author author){
        String res=authorService.addAuthor(author);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @GetMapping("/GetAllAuthor")
    public List<Author> GetAllAuthor(){
        List<Author> authorList=authorService.GetAllAuthor();
        return authorList;
    }
}
