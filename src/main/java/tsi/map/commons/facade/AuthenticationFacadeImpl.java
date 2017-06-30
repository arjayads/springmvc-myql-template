package tsi.map.commons.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tsi.map.model.User;
import tsi.map.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Autowired
    UserRepo userRepo;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getLoggedIn() {
        try {

            Authentication authentication = this.getAuthentication();
            String curUsername = authentication.getName();
            User user = userRepo.findOneByUsername(curUsername);
            return user;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> getUserRoles() {
        List<Integer> ids = new ArrayList<>();

        User user = this.getLoggedIn();
        List<Object[]> roles = userRepo.findRolesByUserId(user.getId());
        if (! roles.isEmpty()) {
            for (Object[] roleObj:roles) {
                ids.add((Integer)roleObj[0]);
            }
        }
        return ids;
    }
}
