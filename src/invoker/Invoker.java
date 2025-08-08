package invoker;
import java.util.Stack;
import commands.*;
import customexception.CustomException;

public class Invoker {
    private Command[] cmdToExecute;

    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    public void executeCommand(Stack<Command> history){
        try{
            for (Command cmd : cmdToExecute) {
                cmd.execute();
                if (cmd.shouldRecord()) {
                    history.push(cmd);
                }
            }
        }catch(CustomException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("Command Failed", e);
        }

    }
}
