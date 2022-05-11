package com.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AppRegex {
    USERNAME("\\w+"), PASSWORD("\\w+");

    String regex;

    AppRegex(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher m = Pattern.compile(this.regex).matcher(input);
        if (m.matches())
            return m;
        return null;
    }
}
