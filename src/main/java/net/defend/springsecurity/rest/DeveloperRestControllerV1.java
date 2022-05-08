package net.defend.springsecurity.rest;

import net.defend.springsecurity.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private List<Developer> DEVELOPERS =
            new ArrayList<>(List.of(new Developer(1L, "Ivan", "Ivanov"),
                    new Developer(2L, "Sasha", "Sergeev"),
                    new Developer(3L, "Sveta", "Kalygina"),
                    new Developer(4L, "Masha", "Valereva")));

    @GetMapping
    @PreAuthorize("hasAuthority('developer:read')")
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:read')")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('developer:write')")
    public Developer create(@RequestBody Developer developer){
        DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:write')")
    public void deleteDeveloperById(@PathVariable Long id){
        DEVELOPERS.removeIf(p -> p.getId().equals(id));
    }
}
