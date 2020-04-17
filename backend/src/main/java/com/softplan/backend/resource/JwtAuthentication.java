package com.softplan.backend.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.softplan.backend.config.JwtTokenUtil;
import com.softplan.backend.dto.JwtRequestDTO;
import com.softplan.backend.dto.JwtResponseDTO;
import com.softplan.backend.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@RequestParam String username, String password) throws Exception {
        JwtRequestDTO request = new JwtRequestDTO(username, password);
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDTO(token, username, jwtTokenUtil.mapAuthorities(userDetails)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("Usuário desabilidato", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciais inválidas", e);
        }
    }
}