package liga.shop.shop.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProfitEntity {

    Long id;

    Double amount;

    LocalDate date;

}
