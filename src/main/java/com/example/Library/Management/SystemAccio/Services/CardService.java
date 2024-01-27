package com.example.Library.Management.SystemAccio.Services;

import com.example.Library.Management.SystemAccio.Enum.CardStatus;
import com.example.Library.Management.SystemAccio.Models.*;
import com.example.Library.Management.SystemAccio.Repsoitory.CardRepository;
import com.example.Library.Management.SystemAccio.Repsoitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;

    public long revenue=0;
    public void generatePlainCard() {
        LibraryCard card=new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        cardRepository.save(card);
    }



    public String AssociateStudent(Integer studentId, Integer cardId) {
       Student student=studentRepository.findById(studentId).get();
       LibraryCard libraryCard=cardRepository.findById(cardId).get();
       libraryCard.setCardStatus(CardStatus.ACTIVE);
       libraryCard.setNameOnCard(student.getName());
       libraryCard.setStudent(student);
       student.setLibraryCard(libraryCard); //Bidirection Mapping
       studentRepository.save(student);
       return "Student is Successfully Associated with library card";

    }
}
