package net.defend.springsecurity.rest;

import net.defend.springsecurity.model.Developer;
import org.springframework.web.bind.annotation.*;

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
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Developer create(@RequestBody Developer developer){
        DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloperById(@PathVariable Long id){
        DEVELOPERS.removeIf(p -> p.getId().equals(id));
    }
}
