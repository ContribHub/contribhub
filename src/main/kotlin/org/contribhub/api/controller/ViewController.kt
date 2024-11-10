package org.contribhub.api.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Deprecated("View Controller is will removed after front-end implementation")
@Controller
class ViewController(
    @Value("\${api.github.client-id}") private val clientId: String,
) {
    @GetMapping("/login")
    fun loginPage(model: Model): String {
        model.addAttribute("clientId", clientId)
        return "loginView"
    }
}
