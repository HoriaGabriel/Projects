package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Publisher;
import library.repository.PublisherRepository;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();
        Iterable<Publisher> all = publisherRepository.findAll();
        all.forEach(publishers::add);
        return publishers;
    }

}
