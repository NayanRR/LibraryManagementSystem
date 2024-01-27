package com.example.Library.Management.SystemAccio.Repsoitory;

import com.example.Library.Management.SystemAccio.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Author findAuthorByName(String authorname);
}
