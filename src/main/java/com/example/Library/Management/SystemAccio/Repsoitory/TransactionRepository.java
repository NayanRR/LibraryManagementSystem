package com.example.Library.Management.SystemAccio.Repsoitory;

import com.example.Library.Management.SystemAccio.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Library.Management.SystemAccio.Enum.*;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTransactionByBookAndLibraryCardAndTransactionStatus(Book book, LibraryCard libraryCard,TransactionStatus transactionStatus);
}
