package com.example.Library.Management.SystemAccio.Controller;

import com.example.Library.Management.SystemAccio.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;


    //Issue Book
    @PostMapping("/IssueBook")
    public ResponseEntity IssueBook(@RequestParam("BookId") Integer bookId,@RequestParam("CardId") Integer cardId ) throws Exception {
        String res=transactionService.issueBook(bookId,cardId);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PostMapping("/ReturnBook")
    public ResponseEntity ReturnBook(@RequestParam("BookId") Integer bookId,@RequestParam("CardId") Integer cardId) throws Exception{
        String res=transactionService.returnBook(bookId,cardId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    //return Book



}
