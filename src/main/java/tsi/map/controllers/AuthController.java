package tsi.map.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tsi.map.commons.facade.AuthenticationFacade;
import tsi.map.model.User;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = "/check")
    @ResponseBody
    public Map check() {
        Map data = new HashMap();
        try {

            User user = authenticationFacade.getLoggedIn();
            if (user != null) {
                data.put("authentic", true);
                data.put("userId", user.getId());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
