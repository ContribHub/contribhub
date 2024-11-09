package org.contribhub.api.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ViewController(
    @Value("\${api.github.client-id}") private val clientId: String,
) {
    @RequestMapping("/login")
    fun loginPage(model: Model): String {
        model.addAttribute("clientId", clientId)
        return "loginView"
    }
}
