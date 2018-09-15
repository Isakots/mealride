package hu.student.projlab.mealride;


import hu.student.projlab.mealride.user.UserService;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import hu.student.projlab.mealride.topic.Topic;
import hu.student.projlab.mealride.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {


    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/")
    public String IndexResponse(Model model) {
        Topic topic = new Topic();
        model.addAttribute("topic", topic);
        return "index";
    }

    @GetMapping("/listtopics")
    public String listTopics(Model model) {
        model.addAttribute("topics", topicService.getAllTopics());
        return "listtopics";
    }

    @GetMapping("/listrest")
    public String listRests(Model model) {
        model.addAttribute("rests", restaurantService.getRests());
        return "listrest";
    }

    @GetMapping("/listusers")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "listusers";
    }
    // but this is NOT rest..
    @PostMapping("/")
    public String addTopic(@ModelAttribute(value="topic") Topic topic) {
       topicService.addTopic(topic);
       // redirecting to index.html to view it again..
        return "redirect:/";
    }

}