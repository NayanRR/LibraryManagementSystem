package com.example.Library.Management.SystemAccio.Repsoitory;

import com.example.Library.Management.SystemAccio.Controller.StudentController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Library.Management.SystemAccio.Models.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}