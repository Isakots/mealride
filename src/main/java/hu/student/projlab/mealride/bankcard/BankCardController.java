package hu.student.projlab.mealride.bankcard;

import hu.student.projlab.mealride.exception.BankCardException;
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
        model.addAttribute("cards", bankCardService.getBankcards());
        return "user/cards";
    }

    @GetMapping("/newcard")
    public String getNewCardForm(Model model) {
        model.addAttribute("card", new BankCard());
        return "user/newcard";
    }

    @PostMapping("/newcard")
    public ModelAndView addCard(ModelAndView modelAndView, @ModelAttribute(value = "card") BankCard card, BindingResult results) {

        if (results.hasErrors()) {
            modelAndView.setViewName("user/newcard");
            modelAndView.addObject("errorOccured", "Some errors occured. Please try again!");
            return modelAndView;
        }

        try {

            bankCardService.cardValidation(card);

        } catch (BankCardException exception) {
            modelAndView.addObject("errorOccured", exception.getMessage());
            modelAndView.setViewName("user/newcard");
            return modelAndView;
        }

        bankCardService.addCard(card);
        modelAndView.addObject("cards", bankCardService.getBankcards());
        modelAndView.setViewName("user/cards");
        return modelAndView;
    }

    @PostMapping("/cards/{id}")
    public ModelAndView deleteBankCard(@PathVariable Long id, ModelAndView modelAndView) {
        bankCardService.deleteCard(id);
        modelAndView.addObject("cards", bankCardService.getBankcards());
        modelAndView.setViewName("user/cards");
        return modelAndView;
    }

}
