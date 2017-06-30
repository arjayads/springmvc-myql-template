package tsi.map.commons.facade;

import org.springframework.security.core.Authentication;
import tsi.map.model.User;

import java.util.List;

public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getLoggedIn();
    List<Integer> getUserRoles();
}
