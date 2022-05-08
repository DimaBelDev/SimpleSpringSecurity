package net.defend.springsecurity.rest;

import net.defend.springsecurity.model.Developer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private List<Developer> DEVELOPERS =
            new ArrayList<>(List.of(new Developer(1L, "Ivan", "Ivanov"),
                    new Developer(2L, "Sasha", "Sergeev"),
                    new Developer(3L, "Sveta", "Kalygina"),
                    new Developer(4L, "Masha", "Valereva")));

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable long id){
        return DEVELOPERS.stream().filter(p -> p.getId() == id).findFirst().orElse(null);

    }
}
