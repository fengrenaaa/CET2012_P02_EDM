package commands;
import customexception.CustomException;
/**
 * The {@code Command} interface defines the structure for all command operations
 * in the application. It has execute, undo, and shouldRecord (for undo) methods.
 */
public interface Command {
    /**
     * Executes the command's main operation.
     * @throws CustomException if an error occurs during execution, such as invalid input.
     */
    void execute() throws CustomException;
    /**
     * Reverses the operation performed by {@code execute()}.
     * @throws CustomException if the undo operation fails.
     */
    void undo() throws CustomException;
    /**
     * Indicates whether this command should be recorded in history for undo
     * @return {@code true} if the command should be recorded; {@code false} otherwise.
     */
    boolean shouldRecord();
}
