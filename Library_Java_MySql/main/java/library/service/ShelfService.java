package library.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import library.model.Shelf;
import library.repository.ShelfRepository;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf save(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    public List<Shelf> findAll() {
        List<Shelf> shelfs = new ArrayList<>();
        Iterable<Shelf> all = shelfRepository.findAll();
        all.forEach(shelfs::add);
        return shelfs;
    }
}
