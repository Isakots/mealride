package hu.student.projlab.mealride.bankcard;


import hu.student.projlab.mealride.exception.BankCardException;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankCardService {

    @Autowired
    private BankCardRepository bankCardRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    public List<BankCard> getBankcards() {
        User user = userService.getCurrentUser();
        return bankCardRepository.findAllByUserId(user.getId());
    }

    public void addCard(BankCard card) {
        card.setCvc(bCryptPasswordEncoder.encode(card.getCvc()));
        User user = userService.getCurrentUser();
        card.setUser(user);
        bankCardRepository.save(card);
    }

    void cardValidation(BankCard card) throws BankCardException {

        if(card.getOwnername().equals("")) {
            throw new BankCardException("Owner name must not be empty!");
        }

        if(card.getCvc().length()<3 || card.getCvc().length()>4) {
            throw new BankCardException("CVC length must be beetween 3 and 4 character!");
        }

        if(card.getNumber() == null) {
            throw new BankCardException("Card number must not be empty!");
        }

    }


    public void deleteCard(BankCard card) {
        bankCardRepository.delete(card);
    }


}
