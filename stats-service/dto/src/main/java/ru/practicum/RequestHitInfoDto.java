package ru.practicum;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestHitInfoDto {

    @NotBlank(message = "URI cannot be empty")
    private String uri;
    @NotBlank(message = "User IP cannot be empty")
    private String ip;
    @NotBlank(message = "Application name cannot be empty")
    private String app;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Request time cannot be empty")
    private LocalDateTime timestamp;

    @Builder
    public static RequestHitInfoDto build(String uri, String ip, String app, LocalDateTime timestamp) {
        RequestHitInfoDto requestHitInfoDto = new RequestHitInfoDto(uri, ip, app, timestamp);

        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<RequestHitInfoDto>> violations = validator.validate(requestHitInfoDto);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }

        return requestHitInfoDto;
    }
}