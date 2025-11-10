package be.ucll.crafsmanship.command.gamecontroller;

public class TriangleButtonCommand implements Command {
    private final Receiver receiver;

    public TriangleButtonCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.actionTriangle();
    }
}
