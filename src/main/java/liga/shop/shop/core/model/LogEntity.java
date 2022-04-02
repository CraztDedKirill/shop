package liga.shop.shop.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class LogEntity {

    private Long id;

    private LocalDateTime time;

    private String methodName;

    private String className;

    private String args;
}