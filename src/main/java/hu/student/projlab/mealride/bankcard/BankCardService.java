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

    private BankCardRepository bankCardRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    @Autowired
    public BankCardService(BankCardRepository bankCardRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
        this.bankCardRepository = bankCardRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    public List<BankCard> getBankcards() {
        return bankCardRepository.findAllByUserId(userService.getCurrentUser().getId());
    }

   void addCard(BankCard card) {
        card.setCvc(bCryptPasswordEncoder.encode(card.getCvc()));
        card.setUser(userService.getCurrentUser());
        card.setCreated_at(System.currentTimeMillis());
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

    void deleteCard(Long id) {
        BankCard card = getCardByNumber(id);
        card.setUser(null);
        card.setDeleted_at(System.currentTimeMillis());
        bankCardRepository.save(card);
    }

    public BankCard getCardByNumber(Long number) {
        return bankCardRepository.getBankCardByNumber(number);
    }


}
