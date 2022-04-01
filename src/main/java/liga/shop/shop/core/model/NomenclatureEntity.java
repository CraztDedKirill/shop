package liga.shop.shop.core.model;

import lombok.Data;

@Data
public class NomenclatureEntity {

    private Long id;

    private String name;

    private String availability;

    private String price;

    private String describe;

    private Long type_id;

}