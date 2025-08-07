import java.util.Stack;

public class UndoCommand implements Command{
    private Stack<Command> history;
    public UndoCommand(Stack<Command> history) {
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
