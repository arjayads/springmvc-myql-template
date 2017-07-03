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
@RequestMapping("/account")
public class AccountController {

    private static String VIEW_BASE_PATH = "account/";

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(VIEW_BASE_PATH+"index");
        return modelAndView;
    }
}
