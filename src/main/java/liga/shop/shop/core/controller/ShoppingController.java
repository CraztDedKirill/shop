package liga.shop.shop.core.controller;

import liga.shop.shop.core.model.ShoppingEntity;
import liga.shop.shop.core.service.ShoppingService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/owner/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping("/save")
    void saveNewShoppingEntity(@RequestBody @Valid ShoppingEntity shoppingEntity) {
        shoppingService.insert(shoppingEntity);
    }

    @GetMapping("/{id}")
    ShoppingEntity getShoppingEntityById(@PathVariable Long id){
        ShoppingEntity shoppingEntity = shoppingService.findById(id);
        return shoppingEntity;
    }


    @GetMapping("/")
    List<ShoppingEntity> getAllShoppingEntity(){
        List<ShoppingEntity> list = shoppingService.findAll();
        return list;
    }

    @DeleteMapping("/deleteById/{id}")
    void deleteShoppingEntityyById(Long id){
        shoppingService.deleteById(id);
    }

    @PatchMapping("/update")
    public void updateAddress(@RequestBody ShoppingEntity shoppingEntity) {
        shoppingService.updateById(shoppingEntity);
    }
}
