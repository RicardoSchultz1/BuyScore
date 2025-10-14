package com.senac.ProjetoPontos.Infrastructure.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.senac.ProjetoPontos.Domain.Entity.Usuario;

public class UsuarioDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioDetails(Usuario usuario) { this.usuario = usuario; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = switch (usuario.getPerfilUsuario()) {
            case 3 -> "ROLE_ADMIN";
            case 2 -> "ROLE_MANAGER";
            default -> "ROLE_USER";
        };
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() { return usuario.getSenha(); }

    @Override
    public String getUsername() { return usuario.getEmail(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public Usuario getUsuario() { return usuario; }
}
