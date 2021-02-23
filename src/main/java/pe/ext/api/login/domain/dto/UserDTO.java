package pe.ext.api.login.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
@ApiModel
public class UserDTO {
    private String username;
    private String token;
    private String tokenId;
}
