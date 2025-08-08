package commands;

import java.util.Stack;
import receiver.Receiver;
import customexception.CustomException;

public class UndoCommand implements Command{
    private Stack<Command> history;
    private Receiver receiver;

    public UndoCommand(Receiver receiver, Stack<Command> history) {
        this.receiver = receiver;
        this.history = history;
    }

    @Override
    public void execute() throws CustomException {
        if (!history.empty()) {
            Command cmd = history.pop();
            cmd.undo();
        }else {
            System.out.println("No commands to undo");
        }

    }

    @Override
    public void undo() {}

    @Override
    public boolean shouldRecord() {return false;}
}
