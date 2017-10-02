package com.narendra.sams.web.auth;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {
    private static final long serialVersionUID = -3455393371128311474L;
    private Collection<GrantedAuthority> authorities;
    private String firstName;
    private Long id;
    private String lastName;
    private String password;
    private String username;

    public UserDetail(Long id, String username, String password, Collection<GrantedAuthority> authorities, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean equals(Object rhs) {
        if (rhs instanceof UserDetail) {
            return this.username.equals(((UserDetail) rhs).username);
        }
        return false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LoggedInUser [id=").append(this.id).append(", username=").append(this.username).append(", password=").append(this.password).append(", authorities=").append(this.authorities).append(", firstName=").append(this.firstName).append(", lastName=").append(this.lastName).append("]");
        return builder.toString();
    }
}
