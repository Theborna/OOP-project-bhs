package com.company;

public class TG {
    private static final String API_KEY = "5421036090:AAGFN42FvfONUIX25ZOM4b4_C8vzr9Z9I1s";
    private static final String LOGGERCHANNELID = "-1001735428152";

    private static TG tg = null;

    private TG() {
        tg = this;
        inito();
    }

    private void inito() {
        TelegramBot bot = new TelegramBot("BOT_TOKEN");
    }

    public static TG getInstance() {
        if (tg == null) {
            tg = new TG();
        }
        return tg;
    }


}
