package hu.student.selflaboratory.mealride.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {

        return topicService.getAllTopics();
    }

    @RequestMapping("/topics/{Id}")
    public Optional<Topic> getTopic(@PathVariable String Id) {
       return topicService.getTopic(Id);
    }

    @RequestMapping(method= RequestMethod.POST,value="/topics")
    public void addTopic(@RequestBody Topic topic) {
        topicService.addTopic(topic);
    }

    @RequestMapping(method= RequestMethod.PUT, value="/topics")
    public void updateTopic(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/topics/{Id}")
    public void deleteTopic(@PathVariable String Id) {
        topicService.deleteTopic(Id);
    }
}
