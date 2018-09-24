package hu.student.projlab.mealride.bankcard;


import hu.student.projlab.mealride.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;

    public List<BankCard> getBankcards(User user) {
        return bankCardRepository.findByUserId(user.getId());
    }

    public void addCard(BankCard card) {
        bankCardRepository.save(card);
    }


    public void deleteCard(BankCard card) {
        bankCardRepository.delete(card);
    }


}
