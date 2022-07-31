package com.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum AppRegex {

    USERNAME(username()), PASSWORD(password()), SWITCH("switch to -(?<next>\\w+)"),
    FULL_NAME(fullName()), BIOGRAPHY("^.{0,250}+$"),
    POST_FROM_DB(
            "%id% = (?<id>\\d+) %text% = (?<text>\\.+) %sender_id% = (?<sender_id>\\d+) %likes% = (?<likes>\\d+) %views% = (?<views>\\d+)"),
    DATE(" ^((?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:(?:0?[13578]|1[02])(-)31)|((0?[1,3-9]|1[0-2])(-)(29|30))))$|^(?:(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(-)0?2(-)29)$|^(?:(?:1[6-9]|2[0-9])\\d{2})(-)(?:(?:0?[1-9])|(?:1[0-2]))(-)(?:0?[1-9]|1\\d|2[0-8])$"),
    // mother of all regex
    CHAT_NAME("^\\S.{0,20}$"), EMAIL("^[a-zA-Z0-9.!#$%&’*+\\/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"),
    ADD_USER("-add (?<username>" + username() + ")"), REMOVE_USER("-remove (?<username>" + username() + ")"),
    CHANGE_PERMISSIONS(
            "-permission (?<username>" + username() + ") (?<permission>(owner)|(admin)|(normal)|(observer))"),
    CHAT_ID(username()),
    SET_PASSWORD("-set -password (?<password>\\w+)"),
    SET_USERNAME("-set -username (?<username>\\w+)"), SET_NAME("-set -name (?<name>\\w+)"),
    SET_BIO("-set -bio (?<bio>.+)"),
    SET_VISIBILITY("-set -visibility (?<visibility>\\w+)"),
    SEARCH_USER("-search")
    ;

    static final String username() {
        return "(?=[a-zA-Z0-9._]{4,20}$)(?!.*[_.]{2})[^_.].*[^_.]";
    };

    static final String password() {
        return "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    }

    static final String fullName() {
        return "\\S+";
    }

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

    public boolean matches(String input) {
        return input.matches(this.regex);
    }
}
