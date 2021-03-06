package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Person;
import library.repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        Iterable<Person> all = personRepository.findAll();
        all.forEach(persons::add);
        return persons;
    }
}
