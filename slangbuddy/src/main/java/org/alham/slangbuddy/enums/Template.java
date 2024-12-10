package org.alham.slangbuddy.enums;

import java.util.Arrays;

public enum Template {

    BASIC, HAM;



    public static Template getTemplate(String template) {
        return Arrays.stream(Template.values()).anyMatch(t -> t.name().equals(template))
                ? Template.valueOf(template) : null;
    }
}
