package liga.shop.shop.core.service;

import liga.shop.shop.core.model.NomenclatureEntity;
import liga.shop.shop.core.model.ShoppingEntity;
import liga.shop.shop.core.model.StorageEntity;
import liga.shop.shop.core.repository.NomenclatureRepository;
import liga.shop.shop.core.repository.ShoppingRepository;
import liga.shop.shop.core.repository.StorageRepository;
import liga.shop.shop.dto.ShoppingDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingService {

    private ShoppingRepository shoppingRepository;
    private StorageRepository storageRepository;
    private NomenclatureRepository nomenclatureRepository;
    private ModelMapper modelMapper;

    public ShoppingService(ShoppingRepository shoppingRepository, StorageRepository storageRepository,
                           NomenclatureRepository nomenclatureRepository, ModelMapper modelMapper) {
        this.shoppingRepository = shoppingRepository;
        this.storageRepository = storageRepository;
        this.nomenclatureRepository = nomenclatureRepository;
        this.modelMapper = modelMapper;
    }

    public void insert(ShoppingEntity shoppingEntity) {

        shoppingRepository.insert(shoppingEntity);
    }

    public ShoppingEntity findById(Long id) {
        ShoppingEntity shoppingEntity = shoppingRepository.findById(id);
        return shoppingEntity;
    }

    public List<ShoppingEntity> findAll() {
        List<ShoppingEntity> list = shoppingRepository.findAll();
        return list;
    }

    public void updateById(ShoppingEntity shoppingEntity) {

        shoppingRepository.updateById(shoppingEntity);
    }

    public void deleteById(Long id) {
        shoppingRepository.deleteById(id);
    }


    //Взаимодействуем с другим модулем(например с кассовым аппартом через RabbitMQ)
    public void insertAuto(ShoppingDto Shoppingdto) {
        ShoppingEntity shoppingEntity = modelMapper.map(Shoppingdto, ShoppingEntity.class);
        shoppingRepository.insert(shoppingEntity);

        StorageEntity storageEntity = storageRepository.findById(shoppingEntity.getType_id());

        storageEntity.setCount(storageEntity.getCount() - shoppingEntity.getAmount());

        storageRepository.updateById(storageEntity);

        if(storageEntity.getCount()<=0){
          NomenclatureEntity nomenclatureEntity = nomenclatureRepository.findById(storageEntity.getNomenclatureId());
            nomenclatureEntity.setAvailability("нет в наличии");
          nomenclatureRepository.updateById(nomenclatureEntity);
        }
    }

}
