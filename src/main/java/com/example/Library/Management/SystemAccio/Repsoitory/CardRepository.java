package com.example.Library.Management.SystemAccio.Repsoitory;

import com.example.Library.Management.SystemAccio.Models.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard,Integer> {
}
