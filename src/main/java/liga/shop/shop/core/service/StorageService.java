package liga.shop.shop.core.service;

import liga.shop.shop.core.model.StorageEntity;
import liga.shop.shop.core.repository.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StorageService {

    private StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public void insert(StorageEntity storageEntity) {
        storageRepository.insert(storageEntity);
    }

    public List<StorageEntity> findAll() {
        return storageRepository.findAll();
    }

    public StorageEntity findById(Long id) {
        return storageRepository.findById(id);
    }

    public StorageEntity findByTypeId(Long id) {
        return storageRepository.findByTypeId(id);
    }

    public void deleteById(Long id) {
        storageRepository.deleteById(id);
    }

    public void updateById(StorageEntity storageEntity) {

        storageRepository.updateById(storageEntity);
    }

}
