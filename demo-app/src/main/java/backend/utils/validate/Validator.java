package backend.utils.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String DATE_REGEX = "^(201[89]|202[0-3])-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

    public static boolean isValidDate(String inputDate) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(inputDate);
        return matcher.matches();
    }
    private static final String KEYWORD_REGEX = "^[a-zA-Z0-9.# ]*$";

    public static boolean isValidKeyword(String keyword) {
        Pattern pattern = Pattern.compile(KEYWORD_REGEX);
        Matcher matcher = pattern.matcher(keyword);

        return matcher.matches() && !keyword.isEmpty();
    }
}
