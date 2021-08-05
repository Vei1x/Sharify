package com.sharify.webuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
public class WebUser implements UserDetails {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequnce",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private WebUserRole webUserRole;
    private Boolean locked = false;
    private Boolean enabled = true;


    public WebUser(String firstName, String lastName, String email,
                   String password, WebUserRole webUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.webUserRole = webUserRole;
    }

    public WebUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public WebUserRole getWebUserRole() {
        return webUserRole;
    }

    public String getEmail() { return email; }

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

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWebUserRole(WebUserRole webUserRole) {
        this.webUserRole = webUserRole;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebUser webUser = (WebUser) o;
        return Objects.equals(id, webUser.id) && Objects.equals(getFirstName(), webUser.getFirstName()) && Objects.equals(getLastName(), webUser.getLastName()) && Objects.equals(email, webUser.email) && Objects.equals(getPassword(), webUser.getPassword()) && Objects.equals(getWebUserRole(), webUser.getWebUserRole()) && Objects.equals(locked, webUser.locked) && Objects.equals(isEnabled(), webUser.isEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstName(), getLastName(), email, getPassword(), getWebUserRole(), locked, isEnabled());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(webUserRole.name());
        return Collections.singletonList(authority);
    }
}
