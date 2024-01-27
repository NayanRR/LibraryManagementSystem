package com.example.Library.Management.SystemAccio.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Author")
@Getter
@Setter
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    @Column(unique = true,nullable = false)
    private Integer authorName;
    private Integer authorage;
    private double rating;
    //One Author Can write many book
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> bookList;

}
