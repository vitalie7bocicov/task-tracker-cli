package model;

public enum Operation {
    ADD, UPDATE, DELETE, MARK_IN_PROGRESS, MARK_DONE, LIST, LIST_TODO, LIST_PROGRESS, LIST_DONE ;

    public static Operation getOperation(String cmd) {
        if ("TODO,PROGRESS,DONE".contains(cmd)) {
            cmd = "LIST_" + cmd;
        }
        return Operation.valueOf(cmd.replace("-", "_"));
    }
}
