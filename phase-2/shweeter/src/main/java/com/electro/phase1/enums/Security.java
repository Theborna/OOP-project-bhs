package com.electro.phase1.enums;

import java.util.Random;

public enum Security {

    CITY("What city were you born in?"),
    EXAM("What was the first exam you failed?"),
    NICKNAME("What was your childhood nickname?"),
    FRIEND("What is the name of your favorite childhood friend?"),
    TEACHER("What was the last name of your third grade teacher?"),
    PET("What was the name of your first pet?"),
    SCHOOL("What school did you attend for sixth grade?"),
    BORN("What time of the day were you born?"),
    NAME("What is your grandmother's first name?"),
    BP("what score did you get in your basic programming course?");

    private String question;

    Security(String text) {
        this.question = text;
    }

    @Override
    public String toString() {
        return this.question;
    }

    public static Security randomQuestion() {
        return values()[new Random().nextInt(Security.values().length)];
    }

    public static Security getQuestion(String ans) {
        for (Security s : Security.values())
            if (ans.equals(s.toString()))
                return s;
        return null;
    }
}
