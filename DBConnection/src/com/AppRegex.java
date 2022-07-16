package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AppRegex {
    USERNAME("\\w+"), PASSWORD("\\w+"), SWITCH("switch to -(?<next>\\w+)"),
    POST_FROM_DB(
            "%id% = (?<id>\\d+) %text% = (?<text>\\.+) %sender_id% = (?<sender_id>\\d+) %likes% = (?<likes>\\d+) %views% = (?<views>\\d+)"),
            ;
    
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
