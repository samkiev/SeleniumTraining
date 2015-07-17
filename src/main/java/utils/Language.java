package utils;

import java.util.Locale;

public enum Language {

    EN,
    FR,
    RU,
    DE;

    public static Language getCurrentLanguage() {
        return valueOf(System.getProperty("language", "ru").toUpperCase());
    }

    public Locale toLocale() {
        switch (this) {
            case EN:
                return Locale.ENGLISH;
            case FR:
                return Locale.FRANCE;
            case DE:
                return Locale.GERMANY;
            case RU:
                return Locale.forLanguageTag("ru");
        }
        throw new IllegalStateException("The language is not supported");
    }
}
