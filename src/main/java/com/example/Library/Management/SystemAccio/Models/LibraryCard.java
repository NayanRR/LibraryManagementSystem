package com.example.Library.Management.SystemAccio.Models;


import com.example.Library.Management.SystemAccio.Controller.StudentController;
import com.example.Library.Management.SystemAccio.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LibraryCard")
@Getter
@Setter
@Data
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardID;

    //card status must carry one value out of ALL PREDEFINED VALUES
    @Enumerated(value= EnumType.STRING)
    private CardStatus cardStatus;

    private String nameOnCard;

    private Integer noOfBooksIssued=0;

   @OneToOne
   @JoinColumn
   private Student student;

   @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
   private List<Transaction> transactionList=new ArrayList<>();

}
