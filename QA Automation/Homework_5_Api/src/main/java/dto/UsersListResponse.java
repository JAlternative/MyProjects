package dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO
 *
 * @author Сергей Хорошков
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersListResponse {
    private Integer page;
    @JsonProperty("per_page")
    private Integer perPage;
    private Integer total;
    @JsonProperty("total_pages")
    private Integer totalPages;
    private List<DataListUsersResponse> data;
    private SupportResponse support;
}
