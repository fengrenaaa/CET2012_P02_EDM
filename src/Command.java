public interface Command {
    void execute() throws CustomException;
    void undo() throws CustomException;
    boolean shouldRecord();
}
