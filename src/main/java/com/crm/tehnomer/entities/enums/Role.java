package com.crm.tehnomer.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN_ROLE,
    CLIENT_ROLE,
    SALER_ROLE,
    FACTORY_ROLE,
    MASTER_ROLE,
    LEADER_ROLE,
    HR_ROLE,
    ACCOUNTANT_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
