package liga.shop.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDataDto {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
