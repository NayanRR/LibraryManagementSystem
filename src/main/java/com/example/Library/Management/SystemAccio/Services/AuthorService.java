package com.example.Library.Management.SystemAccio.Services;

import com.example.Library.Management.SystemAccio.Models.Author;
import com.example.Library.Management.SystemAccio.Models.Book;
import com.example.Library.Management.SystemAccio.Repsoitory.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    public List<String> GetAllBookk(String authorname) {
        Author author=authorRepository.findAuthorByName(authorname);
        List<Book> bookList=author.getBookList();
        List<String> bookName=new ArrayList<>();
        for(Book book :bookList){
            bookName.add(book.getBookName());
        }
        return bookName;

    }

    public List<String> GetAllBook(Integer authorID) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(authorID);
        List<String> bookName2;
        if (!optionalAuthor.isPresent()) {
            throw new Exception("Author ID is Invalid");
        } else {
            Author author = optionalAuthor.get();
            List<Book> bookList = author.getBookList();
            bookName2 = new ArrayList<>();
            for (Book book : bookList) {
                bookName2.add(book.getBookName());
            }
        }
        return bookName2;


    }

    public String addAuthor(Author author) {
        authorRepository.save(author);
        return "Author is been added in db";
    }

    public List<Author> GetAllAuthor() {
        return authorRepository.findAll();
    }

    public double AvgRatingOfAllTheBook(Integer authorId){
        Author author=authorRepository.findById(authorId).get();
        List<Book> bookList=author.getBookList();
        double avg=0.00;
        for(Book books:bookList){
            avg=avg+books.getRating();
        }
        return avg;
    }
}
