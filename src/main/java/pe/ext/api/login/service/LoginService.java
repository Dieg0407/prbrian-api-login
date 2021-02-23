package pe.ext.api.login.service;

import com.microsoft.aad.msal4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.ext.api.login.configuration.properties.AzureProperties;
import pe.ext.api.login.domain.dto.LoginDTO;
import pe.ext.api.login.domain.dto.UserDTO;
import pe.ext.api.login.domain.service.ILoginService;

import java.util.Set;
import static java.util.Collections.*;

@Service
public class LoginService implements ILoginService {
    @Autowired AzureProperties azureProperties;

    @Override
    public UserDTO login(LoginDTO data) {
        return connectToAzureDirectory(data);
    }

    public UserDTO connectToAzureDirectory(LoginDTO data) {
        try {
            System.out.println(azureProperties);

            var client = PublicClientApplication.builder(azureProperties.getClientId())
                    .authority(azureProperties.getAuthority())
                    .build();

            Set<IAccount> accountsInCache = client.getAccounts().join();
            var account = getAccountByUsername(accountsInCache, data.getUsername());
            var result = acquireTokenUsernamePassword(
                    client,
                    singleton(azureProperties.getScope()),
                    account,
                    data.getUsername(),
                    data.getPassword()
            );

            return UserDTO.builder()
                    .username(result.account().username())
                    .token(result.accessToken())
                    .tokenId(result.idToken())
                    .build();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private IAuthenticationResult acquireTokenUsernamePassword(PublicClientApplication pca,
                                                               Set<String> scope,
                                                               IAccount account,
                                                               String username,
                                                               String password) throws Exception {
        IAuthenticationResult result;
        try {
            var silentParameters = SilentParameters
                    .builder(scope)
                    .account(account)
                    .build();

            result = pca.acquireTokenSilently(silentParameters).join();
        } catch (Exception ex) {
            if (ex.getCause() instanceof MsalException) {
                var parameters = UserNamePasswordParameters
                        .builder(scope, username, password.toCharArray())
                        .build();

                result = pca.acquireToken(parameters).join();
            } else {
                throw ex;
            }
        }
        return result;
    }

    private IAccount getAccountByUsername(Set<IAccount> accounts, String username) {
        if (accounts.isEmpty()) {
            return null;
        }
        for (IAccount account : accounts) {
            if (account.username().equals(username)) {
                return account;
            }
        }
        return null;
    }
}
