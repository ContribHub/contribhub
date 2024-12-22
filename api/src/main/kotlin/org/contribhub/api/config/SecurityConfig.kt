package org.contribhub.api.config

import jakarta.servlet.http.HttpServletResponse
import org.contribhub.api.controller.filter.GithubAuthFilter
import org.contribhub.core.service.UserAuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val userAuthService: UserAuthService,
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .formLogin { it.disable() }
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint())
            }.addFilterBefore(GithubAuthFilter(userAuthService), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.GET, "/login", "/github/login/callback", "/error")
                    .permitAll()
                    .requestMatchers(
                        HttpMethod.GET,
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/health",
                        "/languages",
                        "/licenses",
                        "/topics",
                        "/repositories/**",
                    ) // 기본 검색용 api 허용
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }.build()

    private fun authenticationEntryPoint() =
        AuthenticationEntryPoint { _, response, _ ->
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
}
