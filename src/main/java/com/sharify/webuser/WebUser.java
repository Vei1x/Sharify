package com.sharify.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
public class WebUser implements UserDetails {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequnce",
            allocationSize = 1
    )
    @Id
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private String name;
    private String surname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private WebUser webUserRole;
    private Boolean locked;
    private Boolean enabled;

    public WebUser(String name,
                   String surname,
                   String email,
                   String password,
                   WebUser webUserRole,
                   Boolean locked,
                   Boolean enabled) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.webUserRole = webUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }
    public WebUser() {
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public WebUser getWebUserRole() {
        return webUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(webUserRole.name + " " + webUserRole.surname);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
