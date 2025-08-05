import java.util.Stack;

public class Invoker {
    private Command[] cmdToExecute;
//    public Invoker(Command command) {
//        this.command = command;
//    }

    public void setCommandsForExecution(Command[] cmdToExecute) {
        this.cmdToExecute = cmdToExecute;
    }

    public void executeCommand(Stack<Command> history) {
        for (Command cmd : cmdToExecute) {
            cmd.execute();
            if (cmd.shouldRecord()) {
                history.push(cmd);
            }
        }
    }
}
