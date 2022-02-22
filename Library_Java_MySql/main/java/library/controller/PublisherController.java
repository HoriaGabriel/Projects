package library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.PublisherService;
import library.model.Publisher;
import library.validator.PublisherValidator;

@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private PublisherValidator publisherValidator;

    @PostMapping("/publisher")
    public Publisher save(Publisher publisher) {
        publisherValidator.validate(publisher);
        return publisherService.save(publisher);
    }

    public void  validate(Publisher publisher){
        publisherValidator.validate(publisher);
    }


    @GetMapping("/publisher")
    public List<Publisher> findAll() {
        return publisherService.findAll();
    }

}
