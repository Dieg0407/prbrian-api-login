package pe.ext.api.login.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class LoginDTO implements Serializable {
    private String username;
    private String password;
}
