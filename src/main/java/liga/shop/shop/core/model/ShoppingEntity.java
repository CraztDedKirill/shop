package liga.shop.shop.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingEntity {

    private Long id;

    private Integer amount;

    private Long type_id;

}
