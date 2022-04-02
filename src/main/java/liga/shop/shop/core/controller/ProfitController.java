package liga.shop.shop.core.controller;

import liga.shop.shop.core.model.ProfitEntity;
import liga.shop.shop.core.service.ProfitService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping()
public class ProfitController {

    private ProfitService profitService;

    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GetMapping("/admin/profit/{id}")
    ProfitEntity getProfitEntityById(@PathVariable Long id){
        ProfitEntity profitEntity = profitService.findById(id);
        return profitEntity;
    }


    @GetMapping("/admin/profit/")
    List<ProfitEntity> getAllProfitEntity(){
        List<ProfitEntity> list = profitService.findAll();
        return list;
    }

    @PatchMapping("/owner/update")
    public void updateProfitEntity(@RequestBody ProfitEntity profitEntity) {
        profitService.updateById(profitEntity);
    }
}
