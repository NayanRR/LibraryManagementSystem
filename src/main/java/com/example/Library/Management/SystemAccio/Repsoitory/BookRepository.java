package com.example.Library.Management.SystemAccio.Repsoitory;

import com.example.Library.Management.SystemAccio.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
