package commands;

import java.util.Stack;
import receiver.Receiver;
import customexception.CustomException;

/**
 * Represents a command that undoes the last command
 * from the history stack.
 *
 * <p>This command itself is not recorded in history,
 * and its {@code undo()} method does nothing.
 */
public class UndoCommand implements Command{
    /**
     * The history stack storing previously executed commands.
     */
    private Stack<Command> history;
    /**
     * The receiver that performs data operations.
     */
    private Receiver receiver;

    /**
     * Constructs an UndoCommand with the given receiver and command history.
     *
     * @param receiver the receiver used by commands (not directly used in undo).
     * @param history the stack containing previously executed commands.
     */
    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    /**
     * Executes the undo operation by popping the most recent command
     * from the history stack and calling its {@code undo()} method.
     *
     * @throws CustomException if the undo operation of the popped command fails.
     */
    @Override
    public void execute() throws CustomException {
        if (!history.empty()) {
            Command cmd = history.pop();
            cmd.undo();
        }else {
            System.out.println("No commands to undo");
        }

    }

    /**
     * This command itself has no undo operation.
     */
    @Override
    public void undo() {}

    /**
     * Indicates whether this command should be recorded.
     *
     * @return {@code false} because undo operations are not recorded.
     */
    @Override
    public boolean shouldRecord() {return false;}
}
