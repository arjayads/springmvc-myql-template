/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tsi.map.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import tsi.map.commons.facade.AuthenticationFacade;


@Controller
public class PagesController {
    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String pageNotFound() {
        return "404";
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String fourZeroThree() {
        return "403";
    }

    @RequestMapping(value = {"/logoutSuccess"}, method = RequestMethod.GET)
    public String logoutSuccess() {
        Authentication auth = authenticationFacade.getAuthentication();
        if (auth == null) {
            return  "logout";
        } else {
            System.out.println("Logging out...");
            return "redirect:/logout";
        }
    }
}
