package liga.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProfitDto {

    Double amount;

    LocalDate date;

}
