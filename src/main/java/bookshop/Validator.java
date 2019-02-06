package bookshop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public boolean validateInputStrings(String givenString) {
        Pattern patternNames = Pattern.compile("[A-Z][a-z]+");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateInputAge(String givenString) {
        Pattern patternNames = Pattern.compile("[0-9]{2,}");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateInputIsbn(String givenString) {
        Pattern patternNames = Pattern.compile("[0-9]+");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateInputId(String givenString) {
        Pattern patternNames = Pattern.compile("[1-9][0-9]*");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateTitle(String givenString) {
        Pattern patternNames = Pattern.compile("[A-ZĄĘŁŃÓŚŹŻ][a-ząćęłńóśźż]*[(?[ ][A-Za-z0-9ąćęłńóśźżĄĘŁŃÓŚŹŻ\n])?]*?");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateYear(String givenString) {
        Pattern patternNames = Pattern.compile("[1-9][0-9]{0,3}");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }

    public boolean validateCoverType(String givenString) {
        Pattern patternNames = Pattern.compile("[Mm|Tt]");
        Matcher m = patternNames.matcher(givenString);
        return m.matches();
    }
}
