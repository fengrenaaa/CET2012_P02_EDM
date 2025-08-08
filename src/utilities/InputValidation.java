package utilities;

import java.util.regex.Pattern;
/**
 * A utility class for validating user input for different command operations.
 * <p>
 * Includes checks for input length, index validation, string formatting, and
 * basic email format verification.
 */
public class InputValidation {
    /**
     * Regex to ensure no consecutive dots or hyphen-dot combinations are used.
     */
    private final static String forbiddenPatternRegex = "(?!.*(\\.\\.|\\.-|-\\.|--))";
    /**
     * Regex to prevent the email from starting with a dot or hyphen.
     */
    private final static String forbiddenStartRegex = "(?![.-])";
    /**
     * Regex to prevent the email from ending with a dot or hyphen.
     */
    private final static String forbiddenEndRegex = "(?<![.-])";
    /**
     * Complex regex to validate standard email formats.
     */
    private final static String emailCheckRegex0 = "(^"+forbiddenPatternRegex+forbiddenStartRegex+
            "[a-zA-Z0-9_.-]+"+forbiddenEndRegex+"@"+forbiddenPatternRegex+forbiddenStartRegex+"[a" +
            "-zA-Z0-9.-]+"+forbiddenEndRegex+"\\."+"[a-z]{2,3}$)";
    /**
     * Simple regex to allow plain usernames with only alphanumeric characters and underscores.
     */
    private final static String emailCheckRegex1 = "(^[a-zA-Z0-9_]+$)";

    /**
     * Checks whether the input array for the add command is of invalid length.
     *
     * @param inputs the input array
     * @return {@code true} if the array length is not 3; {@code false} otherwise
     */
    public static boolean invalidAddLength(String[] inputs){
        return inputs.length != 3;
    }

    /**
     * Checks whether the input array for the update command is of invalid length.
     *
     * @param inputs the input array
     * @return {@code true} if the length is less than 2 or more than 4; {@code false} otherwise
     */
    public static boolean invalidUpdateLength(String[] inputs){
        return inputs.length < 2 || inputs.length > 4;
    }

    /**
     * Checks whether the input array for the delete command is of invalid length.
     *
     * @param inputs the input array
     * @return {@code true} if the length is not 1; {@code false} otherwise
     */
    public static boolean invalidDeleteLength(String[] inputs){
        return inputs.length != 1;
    }

    /**
     * Validates if the given string is a valid integer (positive or negative).
     *
     * @param input the input string
     * @return {@code true} if input is not a valid integer; {@code false} otherwise
     */
    public static boolean invalidIndexDataType(String input){
        return !input.matches("-?\\d+");
    }

    /**
     * Checks if the index is out of range given the list size.
     *
     * @param index the index to check
     * @param size  the size of the list
     * @return {@code true} if index is ≤ 0 or greater than size; {@code false} otherwise
     */
    public static boolean invalidIndexRange(int index, int size){
        return index <= 0 || index > size;
    }

    /**
     * Checks whether the input string is null or empty.
     *
     * @param input the input string
     * @return {@code true} if input is null or empty; {@code false} otherwise
     */
    public static boolean isEmpty(String input){
        return input == null || input.isEmpty();
    }

    /**
     * Splits the input string into an array using whitespace as delimiter.
     *
     * @param input the input string
     * @return the resulting array of strings
     */
    public static String[] parseInputToArray(String input){
        return input.trim().split("\\s+");
    }

    /**
     * Capitalizes the first character of the input string.
     *
     * @param input the input string
     * @return the string with the first character in uppercase
     */
    public static String titledName(String input){
        return input.substring(0, 1).toUpperCase()+input.substring(1);
    }

    /**
     * Validates the third data field (e.g. email) in the input.
     * <ul>
     *     <li>Returns 1 if it matches a standard email format</li>
     *     <li>Returns 2 if it matches a simple username format</li>
     *     <li>Returns -1 if it's invalid</li>
     * </ul>
     *
     * @param input the input string to validate
     * @return 1 for full email, 2 for username only, -1 for invalid format
     */
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
