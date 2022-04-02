package liga.shop.shop.core.controller;

import liga.shop.shop.core.model.PersonDataEntity;
import liga.shop.shop.core.service.PersonDataService;
import liga.shop.shop.dto.PersonDataDto;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/owner/administration")
public class PersonDataController {

    private PersonDataService personDataService;
    private ModelMapper modelMapper;

    public PersonDataController(ModelMapper modelMapper, PersonDataService personDataService) {
        this.modelMapper = modelMapper;
        this.personDataService = personDataService;
    }
    @PostMapping("/")
    List<PersonDataDto> saveNewPersonDataEntity(){
       return personDataService.findAll();
    }

    @PostMapping("/save")
    void saveNewPersonDataEntity(@RequestBody @Valid PersonDataDto personDataDto) {
        personDataService.insert(personDataDto);
    }

    @GetMapping("/{id}")
    PersonDataDto getPersonDataDtoById(@PathVariable Long id){
        PersonDataDto personDataDto = personDataService.findById(id);
        return personDataDto;
    }

    @GetMapping("/{name}")
    PersonDataDto getPersonDataDtoByName(@PathVariable String name) {
        PersonDataDto personDataDto = personDataService.findByName(name);
        return personDataDto;
    }

    @DeleteMapping("/deleteById/{id}")
    void deletePersonDataDtoById(Long id){
        personDataService.deleteById(id);
    }

    @PatchMapping("/update")
    public void updatePersonData(@RequestBody PersonDataEntity PersonDataEntity) {
        personDataService.updateById(PersonDataEntity);
    }
}