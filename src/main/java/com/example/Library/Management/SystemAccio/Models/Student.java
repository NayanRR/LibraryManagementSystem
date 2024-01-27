package com.example.Library.Management.SystemAccio.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity//This is the structure of table
@Table(name="Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Student {

    @Id//Primary Key
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer studentId;

    private String name;

    private int age;

    @Column(name = "Contact no",nullable = false,unique = true)
    private String mobNo;

    private String emailId;

    private String bloodGroup;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private LibraryCard libraryCard;


    //List of Transaction corresponding to these particular student
//    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
//    private List<Transaction> transactionList;


}
