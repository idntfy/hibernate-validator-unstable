package com.omaha.hibernate;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

public class Resource {
    public Boolean validatedMethodWithListOfEntities(@NotEmpty final String arg0, @Valid final List<Entity> arg1) {
        return true;
    }

    public Boolean validatedMethodWithListOfPrimitives(@NotEmpty final String arg0, final List<String> arg1) {
        return true;
    }
}
