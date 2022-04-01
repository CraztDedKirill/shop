package liga.shop.shop.core.controller;

import liga.shop.shop.core.model.StorageEntity;
import liga.shop.shop.core.service.StorageService;
import liga.shop.shop.dto.StorageDto;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/admin/storage")
public class StorageController {

    private StorageService storageService;
    private ModelMapper modelMapper;

    public StorageController(StorageService storageService, ModelMapper modelMapper) {
        this.storageService = storageService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    void saveNewStorageEntity(@RequestBody @Valid StorageDto storageDto) {
        StorageEntity storageEntity = modelMapper.map(storageDto, StorageEntity.class);
        storageService.insert(storageEntity);
    }

    @GetMapping("/{id}")
    StorageEntity getStorageEntityById(@PathVariable Long id){
        StorageEntity storageEntity = storageService.findById(id);
        return storageEntity;
    }

    @GetMapping("/type/{type_id}")
    StorageEntity getStorageEntityByType(Long type_id){
        StorageEntity storageEntity = storageService.findByTypeId(type_id);
        return storageEntity;
    }

    @GetMapping("")
    List<StorageEntity> getAllStorageEntity(){
        List<StorageEntity> list = storageService.findAll();
        return list;
    }

    @DeleteMapping("/deleteById/{id}")
    void deleteStorageEntityById(Long id){
        storageService.deleteById(id);
    }

    /// написать метод обновления данных
    ///
    ///

}
