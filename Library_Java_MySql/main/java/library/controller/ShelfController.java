package library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import library.service.ShelfService;
import library.model.Shelf;
import library.validator.ShelfValidator;

@RestController
public class ShelfController {

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private ShelfValidator shelfValidator;

    @PostMapping("/shelf")
    public Shelf save(Shelf shelf) {
        shelfValidator.validate(shelf);
        return shelfService.save(shelf);
    }

    public void  validate(Shelf shelf){
        shelfValidator.validate(shelf);
    }


    @GetMapping("/shelf")
    public List<Shelf> findAll() {
        return shelfService.findAll();
    }
}
