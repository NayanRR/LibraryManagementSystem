package com.example.Library.Management.SystemAccio.Services;

import com.example.Library.Management.SystemAccio.Enum.*;
import com.example.Library.Management.SystemAccio.Enum.TransactionStatus;
import com.example.Library.Management.SystemAccio.Exception.BookNotFound;
import com.example.Library.Management.SystemAccio.Exception.CardNotFound;
import com.example.Library.Management.SystemAccio.Repsoitory.BookRepository;
import com.example.Library.Management.SystemAccio.Repsoitory.CardRepository;
import com.example.Library.Management.SystemAccio.Repsoitory.StudentRepository;
import com.example.Library.Management.SystemAccio.Repsoitory.TransactionRepository;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.SybaseSqmToSqlAstConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Library.Management.SystemAccio.Models.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    private final Integer MAX_NO_OF_BOOK_LIMIT=0;
    private long revenue=0;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String issueBook(Integer bookId, Integer cardId) throws Exception{

        //book Id is valid or not
        Optional<Book> optionalBook=bookRepository.findById(bookId);
        if(!optionalBook.isPresent()){
            throw new BookNotFound("Book Id is not Valid");
        }
        Book book=optionalBook.get();


        //cardID is valid or not
        Optional<LibraryCard> optionalLibraryCard=cardRepository.findById(cardId);
        if(!optionalLibraryCard.isPresent()){
            throw new CardNotFound("Card Id is Invalid");
        }
        LibraryCard card=optionalLibraryCard.get();


        //Checking whether the book is available or not
        if(book.isAvailable()==false){
            throw new Exception("Book is Unavailable");
        }

        //Checking whether the card is not expired
        if(!card.getCardStatus().equals(CardStatus.ACTIVE)){
            throw new Exception("Card is Not Active");
        }

        if(card.getNoOfBooksIssued()==MAX_NO_OF_BOOK_LIMIT){
            throw new Exception("Maximum Limit Reach");
        }


        //Setting All the parameters of transaction
        Transaction transaction=Transaction.builder().bookName(book.getBookName()).studentName(card.getNameOnCard())
                                .transactionStatus(TransactionStatus.ISSUED).book(book).libraryCard(card).
                                    build();
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()+1);
        card.getTransactionList().add(transaction);//Bidirectional Mapping
        book.setAvailable(false);
        book.getTransactionList().add(transaction);//Bidirectional Mapping


        transactionRepository.save(transaction);
        return "Book is Successfully Issued to StudentID:"+card.getStudent().getStudentId() +" "
                +"Book Id:" + book.getBookId()+" ";


    }

    public String returnBook(Integer bookId, Integer cardId) {
        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

        Transaction transaction=transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatus(book,card,TransactionStatus.ISSUED);
        Date issueDate=transaction.getCreatedOn();

        //Lets calculate the diff between return date and issue date;
        //in built function
        long milliseconds=Math.abs(System.currentTimeMillis()-issueDate.getTime());
        //Convert Milliseconds to days
        long days=TimeUnit.DAYS.convert(milliseconds,TimeUnit.MILLISECONDS);

        int fineAmount=0;
        int fine_per_day=10;
        if(days>15){
            fineAmount=Math.toIntExact(days-15)*fine_per_day;
            revenue=revenue+fineAmount;
        }
        transaction.setFine(fineAmount);
        transaction.setTransactionStatus(TransactionStatus.COMPLETED);
        card.setNoOfBooksIssued(card.getNoOfBooksIssued()-1);
        book.setAvailable(true);
        transaction.setReturnDate(new Date());
        transactionRepository.save(transaction);

        return "The book is Successfully Returned";

    }

    public long revenue(){
        List<Transaction> transactionList=transactionRepository.findAll();
        long revenue=0;
        for(Transaction transaction:transactionList){
            if(transaction.getTransactionStatus()==TransactionStatus.COMPLETED){
                revenue=revenue+transaction.getFine();
            }

        }
        return revenue;
    }

    public String theMostIssuedBook(){
        List<Transaction> transactionList=transactionRepository.findAll();
        HashMap<String,Integer> map=new HashMap<>();
        //Mapping Between Book and The no of times it get issued
        for(Transaction transaction:transactionList){
           String name= transaction.getBook().getBookName();
           int id=transaction.getBook().getBookId();
           map.put(name,map.getOrDefault(id,0)+1);
        }
        int occurence=0;
        String MostIssuedbooks=" ";
        for(String book:map.keySet()){
            if(occurence==0 || map.get(book)>occurence){
                MostIssuedbooks=book;
            }

        }
        return MostIssuedbooks;

    }





}
