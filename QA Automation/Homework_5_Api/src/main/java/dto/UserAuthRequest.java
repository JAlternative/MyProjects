package dto;

import lombok.*;

/**
 * DTO
 *
 * @author Сергей Хорошков
 */
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserAuthRequest {
    @NonNull
    private String email;
    private String password;
}
