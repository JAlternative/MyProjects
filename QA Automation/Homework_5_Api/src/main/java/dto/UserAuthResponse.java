package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO
 *
 * @author Сергей Хорошков
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthResponse {
    private String token;
}
