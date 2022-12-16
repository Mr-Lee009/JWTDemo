package controller;

import com.mta.jwt.demo.payment.model.Card;
import com.mta.jwt.demo.payment.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

    @Autowired
    CardRepo cardRepo;

    @GetMapping(value = "/findAll")
    public List<Card> findAll(){
        return cardRepo.findAll();
    }
}
