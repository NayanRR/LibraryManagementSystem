package com.example.Library.Management.SystemAccio.Models;

import com.example.Library.Management.SystemAccio.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Transactions")
@Getter
@Setter
@Data
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private String bookName;

    private String studentName;

//    private LocalDate issueDate;
//
     private Date returnDate;

    private TransactionStatus transactionStatus;

    private Integer fine;

    @CreationTimestamp
    private Date createdOn;//Handled By spring Internally

    @UpdateTimestamp
    private Date lastModified;//Handled By spring Internally

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;
}
