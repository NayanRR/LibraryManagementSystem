package com.example.Library.Management.SystemAccio.Controller;

import com.example.Library.Management.SystemAccio.Services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/GeneratePlainCard")
    public String GeneratePlainCard(){
        cardService.generatePlainCard();
        return "Plain card is generated ready to associate with new student";
    }

    @PostMapping("/AssociateStudentwithCard")
    public ResponseEntity AssociateStudent(@RequestParam("StudentId") Integer studentId,@RequestParam("CardId") Integer cardId){
        String res=cardService.AssociateStudent(studentId,cardId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
