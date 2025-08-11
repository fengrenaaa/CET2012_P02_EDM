package invoker;
import java.util.Stack;
import commands.*;
import customexception.CustomException;

/**
 * Invoker class that manages and executes commands.
 */
public class Invoker {
    private Command[] cmdToExecute;

    /**
     * Sets a batch of commands to be executed.
     * @param cmdToExecute an array of commands.
     */
    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    /**
     * Executes an array of commands sequentially and push applicable commands to the history stack.
     * @param history the command history stack.
     */
    public void executeCommand(Stack<Command> history){

        for (Command cmd : cmdToExecute) {
            try {
                cmd.execute();
                if (cmd.shouldRecord()) {
                    history.push(cmd);
                }
            }
            catch(CustomException e){
                System.out.println(e.getMessage());
            }
        }

    }
}
