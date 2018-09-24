package hu.student.projlab.mealride.bankcard;


import hu.student.projlab.mealride.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/user/{userId}/cards")
    public String getCards(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<BankCard> cards = bankCardService.getBankcards(user);

        return "cards";
    }




}
