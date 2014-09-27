package com.omaha.hibernate;

import org.hibernate.validator.constraints.NotEmpty;

public class Entity {
    @NotEmpty
    public String string;

    public Entity(final String string) {
        this.string = string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
