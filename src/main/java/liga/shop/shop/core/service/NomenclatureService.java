package liga.shop.shop.core.service;

import liga.shop.shop.core.model.NomenclatureEntity;
import liga.shop.shop.core.repository.NomenclatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NomenclatureService {

    private NomenclatureRepository nomenclatureRepository;

    public NomenclatureService(NomenclatureRepository nomenclatureRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
    }

    public void insert(NomenclatureEntity nomenclatureEntity) {
        nomenclatureRepository.insert(nomenclatureEntity);
    }

    public List<NomenclatureEntity> findAll() {
        return nomenclatureRepository.findAll();
    }

    public NomenclatureEntity findById(Long id) {
        return nomenclatureRepository.findById(id);
    }

    public NomenclatureEntity findByName(String name) {
        return nomenclatureRepository.findByName(name);
    }

    public List<NomenclatureEntity> findAllByType(Long id) {
        return nomenclatureRepository.findAllByTypeId(id);
    }

    public void deleteById(Long id) {
        nomenclatureRepository.deleteById(id);
    }

    public void updateById(NomenclatureEntity nomenclatureEntity) {

        nomenclatureRepository.updateById(nomenclatureEntity);
    }

}
