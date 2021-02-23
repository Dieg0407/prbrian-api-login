package pe.ext.api.login.domain.service;

import pe.ext.api.login.domain.dto.LoginDTO;
import pe.ext.api.login.domain.dto.UserDTO;

public interface ILoginService {
    UserDTO login(LoginDTO data);
}
