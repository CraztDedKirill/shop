package liga.shop.shop.core.service;

import liga.shop.shop.core.model.ProfitEntity;
import liga.shop.shop.core.model.ShoppingEntity;
import liga.shop.shop.core.repository.NomenclatureRepository;
import liga.shop.shop.core.repository.ProfitRepository;
import liga.shop.shop.core.repository.ShoppingRepository;
import liga.shop.shop.dto.ProfitDto;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitService {

    private ProfitRepository profitRepository;
    private ShoppingRepository shoppingRepository;
    private NomenclatureRepository nomenclatureRepository;
    private ModelMapper modelMapper;


    public ProfitService(ProfitRepository profitRepository, ShoppingRepository shoppingRepository,
                         NomenclatureRepository nomenclatureRepository, ModelMapper modelMapper) {
        this.profitRepository = profitRepository;
        this.shoppingRepository = shoppingRepository;
        this.nomenclatureRepository = nomenclatureRepository;
        this.modelMapper = modelMapper;

    }


    public ProfitEntity findById(Long id) {
        ProfitEntity profitEntity = profitRepository.findById(id);
        return profitEntity;
    }

    public List<ProfitEntity> findAll() {
        List<ProfitEntity> list = profitRepository.findAll();
        return list;
    }

    public void updateById(ProfitEntity profitEntity) {

        profitRepository.updateById(profitEntity);
    }

    @Scheduled(cron = " 0 0 1 * * *")
    public void computePrice() throws InterruptedException {

        List<ShoppingEntity> list = new ArrayList(shoppingRepository.findALlByDate(LocalDate.now()));

        Double amount = 0.0;

        for(int i=0; i< list.size(); i++){

            double price = nomenclatureRepository.findById(list.get(i).getType_id()).getPrice();
            int quantity = list.get(i).getAmount();

            amount= amount + price * quantity;

        }

        ProfitDto profitDto = new ProfitDto(amount, LocalDate.now());
        profitRepository.insert(modelMapper.map(profitDto, ProfitEntity.class));

    }
}
