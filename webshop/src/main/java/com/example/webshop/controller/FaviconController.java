package com.yourcompany.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaviconController {

    /**
     * This method handles requests for "favicon.ico" and does nothing,
     * avoiding errors when the browser tries to fetch the icon.
     */
    @RequestMapping("favicon.ico")
    public void favicon() {
        // No-op
    }
}
