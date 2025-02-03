package com.crm.tehnomer.entities.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ADMIN, CLIENT, SALER, FACTORY, MASTER, LEADER, HR, ACCOUNTANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
