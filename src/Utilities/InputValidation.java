package Utilities;

import java.util.regex.Pattern;

public class InputValidation {
    private final static String forbiddenPatternRegex = "(?!.*(\\.\\.|\\.-|-\\.|--))";
    private final static String forbiddenStartRegex = "(?![.-])";
    private final static String forbiddenEndRegex = "(?<![.-])";
    private final static String emailCheckRegex0 = "(^"+forbiddenPatternRegex+forbiddenStartRegex+
            "[a-zA-Z0-9_.-]+"+forbiddenEndRegex+"@"+forbiddenPatternRegex+forbiddenStartRegex+"[a" +
            "-zA-Z0-9.-]+"+forbiddenEndRegex+"\\."+"[a-z]{2,3}$)";
    private final static String emailCheckRegex1 = "(^[a-zA-Z0-9_]+$)";
//    private final static String emailCheckRegexGroups = emailCheckRegex0+"|"+emailCheckRegex1;
    public static boolean invalidAddLength(String[] inputs){
        return inputs.length != 3;
    }
    public static boolean invalidUpdateLength(String[] inputs){
        return inputs.length < 2 || inputs.length > 4;
    }
    public static boolean invalidDeleteLength(String[] inputs){
        return inputs.length != 1;
    }
    public static boolean invalidIndexDataType(String input){
        return !input.matches("-?\\d+");
    }

    public static boolean invalidIndexRange(int index, int size){
        return index <= 0 || index > size;
    }

    public static boolean isEmpty(String input){
        return input == null || input.isEmpty();
    }
    public static String[] parseInputToArray(String input){
        return input.trim().split("\\s+");
    }
    public static String titledName(String input){
        return input.substring(0, 1).toUpperCase()+input.substring(1);
    }
    public static int validateData3 (String input) {
        Pattern emailPattern = Pattern.compile(emailCheckRegex0);
        Pattern simplePattern = Pattern.compile(emailCheckRegex1);
        if (emailPattern.matcher(input).matches()) {
            return 1;
        }else if (simplePattern.matcher(input).matches()) {
            return 2;
        }else {return -1;}

    }
}
