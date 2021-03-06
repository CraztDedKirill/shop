package liga.shop.shop.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NomenclatureEntity {

    private Long id;

    private String name;

    private String availability;

    private Double price;

    private String describe;

    private Long type_id;

}