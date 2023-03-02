package com.mta.jwt.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mta.jwt.demo.utils.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin(HttpServletRequest request, Model model, Principal principal) {
        if(principal!=null){
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("username", loginedUser.getUsername());
        }
        return "admin";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model, Principal principal) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails != null) {
            System.out.printf(userDetails.toString() + " " + userDetails.getAuthorities().toString());
        }

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        request.setAttribute("message", userDetails.getUsername());
        request.setAttribute("rule", userDetails.getAuthorities().toString());
        model.addAttribute("username", loginedUser.getUsername());
        return "homePage";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            model.addAttribute("username", loginedUser.getUsername());
        }
        return "403Page";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal,HttpServletRequest request) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
            model.addAttribute("username", loginedUser.getUsername());
        }

        String[] flowers = new String[] {"Rose","Lily", "Tulip", "Carnation", "Hyacinth" };
        model.addAttribute("flowers", flowers);

        HttpSession session = request.getSession();
        session.setAttribute("ducla","LE ANH DUC");
        return "userInfo";
    }

    //ducla
}
