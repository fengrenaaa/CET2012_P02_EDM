public interface Command {
    void execute();
    void undo();
    boolean shouldRecord();
}
