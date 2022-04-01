package liga.shop.shop.core.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_OWNER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
