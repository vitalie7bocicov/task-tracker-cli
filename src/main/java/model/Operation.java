package model;

public enum Operation {
    ADD, UPDATE, DELETE, MARK_IN_PROGRESS, MARK_DONE, LIST;

    public static Operation getOperation(String cmd) {
        if (cmd.startsWith("MARK")) {
            cmd = cmd.replace("-", "_");
        }
        return Operation.valueOf(cmd);
    }
}
