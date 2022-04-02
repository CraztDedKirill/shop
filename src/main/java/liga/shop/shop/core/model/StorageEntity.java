package liga.shop.shop.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StorageEntity {

    private Long id;

    private Long count;

    private Long nomenclatureId;

}
