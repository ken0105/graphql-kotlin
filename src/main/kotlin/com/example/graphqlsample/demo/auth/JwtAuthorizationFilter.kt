package com.example.graphqlsample.demo.auth

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import java.util.ArrayList
import io.jsonwebtoken.Jwts

const val DUMMY_KEY = "fdsfjsdfdsfsdafdsfdsfklewjifljdklsjfkldsjfkljewklfjiodsjfijweirjewjklfjdskjfkldsjfkljdsfkljdsfdsfsdfdjsklfjskljrueiojfkldsjngvhjoiputf"
const val AUTH_HEADER = "Authorization"
const val JWT_PREFIX = "Bearer "

class JwtAuthorizationFilter(authenticationManager: AuthenticationManager)
    : BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val header = req.getHeader(AUTH_HEADER)
        println(header)
        if (header == null || !header.startsWith(JWT_PREFIX)) {
            chain.doFilter(req, res)
            SecurityContextHolder.getContext().authentication = null
            return
        }

        val authentication = getAuthentication(req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(req: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = req.getHeader(AUTH_HEADER)
        print("token ")
        println(token)
        if (token != null) {
            val user: String = Jwts.parserBuilder()
                .setSigningKey(DUMMY_KEY.toByteArray())
                .build()
                .parseClaimsJws(token.replace(JWT_PREFIX, ""))
                .body
                .subject
            return UsernamePasswordAuthenticationToken(user, null, ArrayList())
        }
        return null
    }
}