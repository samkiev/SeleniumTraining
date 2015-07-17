package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LocaleDateExtractor {

    private static final Map<Language, String> buildHistoryFormats = new HashMap<>();
    private static final Map<Language, String> buildPageFormats = new HashMap<>();


    static {
        buildHistoryFormats.put(Language.EN, "MMM dd, yyyy hh:mm a");
        buildHistoryFormats.put(Language.RU, "dd.MM.yyyy HH:mm");
        buildHistoryFormats.put(Language.FR, "dd MMM yyyy HH:mm");
        buildHistoryFormats.put(Language.DE, "dd.MM.yyyy HH:mm");

        buildPageFormats.put(Language.EN, "MMM dd, yyyy hh:mm:ss a");
        buildPageFormats.put(Language.RU, "dd.MM.yyyy HH:mm:ss");
        buildPageFormats.put(Language.FR, "dd MMM yyyy HH:mm:ss");
        buildPageFormats.put(Language.DE, "dd.MM.yyyy HH:mm:ss");
    }

    private static LocalDateTime parse(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern, Language.getCurrentLanguage().toLocale()));
    }

    private static LocalDateTime getLocalDateTimeForFormat(String textDateTime, Map<Language, String> formatMap) {
        return parse(textDateTime, formatMap.get(Language.getCurrentLanguage()));
    }

    public static LocalDateTime getBuildHistoryCorrectDate(String textDateTime) {
        return getLocalDateTimeForFormat(textDateTime, buildHistoryFormats);
    }

    public static LocalDateTime getBuildPageCorrectDate(String textDateTime) {
        return getLocalDateTimeForFormat(textDateTime, buildPageFormats);
    }

}
