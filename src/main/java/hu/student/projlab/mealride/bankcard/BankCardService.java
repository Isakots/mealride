package hu.student.projlab.mealride.bankcard;


import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.findUserByEmail(name);

        return bankCardRepository.findAllByUserId(user.getId());
    }

    public void addCard(BankCard card) {
        card.setCvc(bCryptPasswordEncoder.encode(card.getCvc()));
        User user = userService.getCurrentUser();
        card.setUser(user);
        bankCardRepository.save(card);
    }


    public void deleteCard(BankCard card) {
        bankCardRepository.delete(card);
    }


}
