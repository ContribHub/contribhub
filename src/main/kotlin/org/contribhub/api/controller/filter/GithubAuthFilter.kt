package org.contribhub.api.controller.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.runBlocking
import org.contribhub.api.service.GithubService
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class GithubAuthFilter(
    private val githubService: GithubService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader != null && authHeader.startsWith(ACCESS_TOKEN_PREFIX)) {
            val accessToken = authHeader.substringAfter(ACCESS_TOKEN_PREFIX)

            val user =
                runBlocking {
                    githubService.getAuthenticatedUser(accessToken).toUserInfo()
                }
            SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        private const val ACCESS_TOKEN_PREFIX = "Bearer "
    }
}
