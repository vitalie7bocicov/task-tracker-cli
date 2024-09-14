package model;

public enum Status {
    TODO, PROGRESS, DONE;

    public static Status valueof(String statusString) {
        if (statusString == null) {
            throw new IllegalArgumentException("Status string cannot be null");
        }

        return switch (statusString.toUpperCase()) {
            case "TODO" -> TODO;
            case "PROGRESS" -> PROGRESS;
            case "DONE" -> DONE;
            default -> throw new IllegalArgumentException("Invalid status " + statusString);
        };
    }
}
