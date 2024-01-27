package com.example.Library.Management.SystemAccio.Services;

import com.example.Library.Management.SystemAccio.Models.Author;
import com.example.Library.Management.SystemAccio.Models.Book;
import com.example.Library.Management.SystemAccio.Repsoitory.AuthorRepository;
import com.example.Library.Management.SystemAccio.Repsoitory.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    public String addbook(Book book, Integer authorID) {

        Author author=authorRepository.findById(authorID).get();
        book.setAuthor(author);
        author.getBookList().add(book);//Bidirectional mapping
        authorRepository.save(author);
        //Author and book is bidirectionally mapped so if you save parent child will automatically get save.
        return "Book is Successfully Stored in DB";
    }
}
