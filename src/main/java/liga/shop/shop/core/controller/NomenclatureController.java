package liga.shop.shop.core.controller;

import liga.shop.shop.core.model.NomenclatureEntity;
import liga.shop.shop.core.service.NomenclatureService;
import liga.shop.shop.dto.NomenclatureDto;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/nomenclature")
public class NomenclatureController{

    private NomenclatureService nomenclatureService;
    private ModelMapper modelMapper;

    public NomenclatureController(NomenclatureService nomenclatureService, ModelMapper modelMapper) {
        this.nomenclatureService = nomenclatureService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/owner/save")
    void saveNewNomenclatureEntity(@RequestBody @Valid NomenclatureDto nomenclatureDto) {
        NomenclatureEntity nomenclatureEntity = modelMapper.map(nomenclatureDto, NomenclatureEntity.class);
        nomenclatureService.insert(nomenclatureEntity);
    }

    @GetMapping("admin/{id}")
    NomenclatureEntity getNomenclatureEntityById(@PathVariable Long id){
        NomenclatureEntity nomenclatureEntity = nomenclatureService.findById(id);
        return nomenclatureEntity;
    }

    @GetMapping("/{name}")
    NomenclatureEntity getNomenclatureEntityByName(@PathVariable String name) {
        NomenclatureEntity nomenclatureEntity = nomenclatureService.findByName(name);
        return nomenclatureEntity;
    }

    @GetMapping("/type/{type_id}")
    List<NomenclatureEntity> getAllNomenclatureEntityByType(Long type_id){
        List<NomenclatureEntity> list = nomenclatureService.findAllByType(type_id);
        return list;
    }

    @GetMapping("/")
    List<NomenclatureEntity> getAllNomenclatureEntity(){
        List<NomenclatureEntity> list = nomenclatureService.findAll();
        return list;
    }

    @DeleteMapping("/owner/deleteById/{id}")
    void deleteNomenclatureEntityById(Long id){
        nomenclatureService.deleteById(id);
    }

    @DeleteMapping("/owner/deleteByName/{name}")
    void deleteNomenclatureEntityByName(String name){
        nomenclatureService.deleteByName(name);
    }

    // сделать запросы update

}