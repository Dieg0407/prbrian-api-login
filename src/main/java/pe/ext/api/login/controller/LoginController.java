package pe.ext.api.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.ext.api.login.domain.dto.LoginDTO;
import pe.ext.api.login.domain.dto.UserDTO;
import pe.ext.api.login.domain.service.ILoginService;

@RestController
@RequestMapping(path = "/auth/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginController {
    @Autowired ILoginService service;

    @PostMapping(path = "/")
    public UserDTO login(@RequestBody LoginDTO data) {
        return service.login(data);
    }

}
