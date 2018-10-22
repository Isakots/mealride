package hu.student.projlab.mealride.bankcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    @GetMapping("/cards/list")
    @ResponseBody
    public List<BankCard> getCards(Model model) {
        BankCard card = new BankCard();
        model.addAttribute("card", card);
        List<BankCard> cards = bankCardService.getBankcards();
        return cards;
    }

    @GetMapping("/cards")
    public String getCard(Model model) {
        BankCard card = new BankCard();
        model.addAttribute("card", card);

        return "cards";
    }

    @PostMapping("/cards")
    public ModelAndView addCard(ModelAndView modelAndView, @ModelAttribute(value="card")BankCard card, BindingResult results) {

        if(results.hasErrors()) {
            modelAndView.setViewName("/cards");
            return modelAndView;
        }

        if(card.getNumber() == null) {
            modelAndView.addObject("numberMustNotBeEmpty", "The card number must not be empty!");
            modelAndView.setViewName("/cards");
            results.reject("number");
            return modelAndView;
        }

        bankCardService.addCard(card);
        modelAndView.setViewName("/cards");
        return modelAndView;
    }

}
