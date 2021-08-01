package com.mta.jwt.demo.controller;

import com.mta.jwt.demo.dao.UserDAO;
import com.mta.jwt.demo.model.UserEntity;
import com.mta.jwt.demo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserDAO userDAO;


    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SA')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_SA')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin() {
        return "admin";
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_SA')")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, Principal principal) {
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        if (loginedUser != null) {
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
        }
        //model.addAttribute("userInfo","ko co noi dung");
        return "homePage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        request.setAttribute("login", "TRANG LOGIN");
        //request.setAttribute("msg",environment.getProperty("msg.loginPage",null,null));
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logout";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

    String a = "select ROWNUM stt, x.*\n" +
            "from (\n" +
            "         select cg.PROVINCE                                province,\n" +
            "                cg.NAME                                    group_name,\n" +
            "                c.CONTRACT_NO,\n" +
            "                c.ISDN,\n" +
            "                (select FULL_NAME\n" +
            "                 from BCCS_PAYMENT.PAY_AREA pa\n" +
            "                 where pa.PAY_AREA_CODE = c.PAY_AREA_CODE) address,\n" +
            "                loc.LAT_SHOP || ' ' || loc.LONG_SHOP       position_xm,\n" +
            "                loc.LAT_USER || ' ' || loc.LONG_USER       position_real,\n" +
            "                loc.DISTANCE,\n" +
            "                DECODE(vc.verify_status_code,\n" +
            "                       '1', 'Chính xác',\n" +
            "                       '0', 'KXD',\n" +
            "                       'Giao lại')                         status\n" +
            "\n" +
            "         from BCCS_PAYMENT.VERIFICATION_MANAGEMENT vm,\n" +
            "              bccs_payment.CONTRACT c,\n" +
            "              bccs_payment.VERIFICATION_CONTRACT_LOCATION loc,\n" +
            "              BCCS_PAYMENT.VERIFICATION_CONTRACT vc\n" +
            "                  left join BCCS_PAYMENT.COLLECTION_GROUP cg on cg.COLLECTION_GROUP_ID = vc.COLLECTION_GROUP_ID\n" +
            "         where 1 = 1\n" +
            "           and c.CONTRACT_ID = vc.CONTRACT_ID\n" +
            "           and loc.VERIFICATION_MNGT_ID = vc.VERIFICATION_MNGT_ID\n" +
            "           and vc.VERIFICATION_MNGT_ID = vm.VERIFICATION_MNGT_ID\n" +
            "           and c.SIGN_DATE >= to_date(?, 'dd/MM/yyyy') -- từ ngày\n" +
            "           and c.SIGN_DATE <= to_date(?, 'dd/MM/yyyy') -- đến ngày\n" +
            "           and vm.COLLECTION_STAFF_ID = ?              -- mã nhân viên\n" +
            "           and vm.COLLECTION_GROUP_ID = ? -- to thu\n" +
            "     ) x\n";
}
