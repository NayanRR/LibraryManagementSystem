package com.example.Library.Management.SystemAccio.Models;

import com.example.Library.Management.SystemAccio.Enum.Genre;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Book")
@Getter
@Setter
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    @Enumerated(value=EnumType.STRING)
    private Genre genre;
    private Integer bookPrice;
    private Integer bookPages;
    private double rating;
    private boolean isAvailable;//if book is issued by somone it will set false

    //Many books can be associated with one book
    @ManyToOne
    @JoinColumn
    private Author author;


    //List of all the transaction happened on these particular book
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<Transaction> transactionList;


}
