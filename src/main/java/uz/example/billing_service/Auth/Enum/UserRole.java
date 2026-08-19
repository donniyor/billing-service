package uz.example.billing_service.Auth.Enum;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {
    ADMIN,
    USER;

    public GrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority("ROLE_" + name());
    }

    public static UserRole fromString(String role) {
        for (UserRole r : values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }

        throw new IllegalArgumentException("Undefited role: " + role);
    }
}
