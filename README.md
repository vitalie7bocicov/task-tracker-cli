# Task Tracker CLI

Task Tracker is a command-line application for managing tasks efficiently. It allows users to add, update, delete, and 
list tasks while storing data in a JSON file.

## Features

- Add, update, and delete tasks
- Mark tasks as **todo**, **in-progress**, or **done**
- List all tasks or filter by status
- Persistent storage using a JSON file
- Simple and lightweight with no external dependencies

## Task Properties

Each task includes the following properties:

- `id` – A unique identifier for the task
- `description` – A short task description
- `status` – The current status (`todo`, `in-progress`, `done`)
- `createdAt` – Timestamp when the task was created
- `updatedAt` – Timestamp when the task was last updated

## Installation

### Prerequisites

Ensure you have **Java 21** installed. You can check your Java version by running:

```sh
java -version
```

## Usage

The CLI accepts commands in the following format:

On Linux or macOS:
```sh
./task-cli [command] [arguments]
```

On Windows:
```sh
./task-cli.bat [command] [arguments]
```

### Adding a Task

```sh
./task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)
```

### Updating a Task

```sh
./task-cli update 1 "Buy groceries and cook dinner"
```

### Deleting a Task

```sh
./task-cli delete 1
```

### Marking a Task as In Progress or Done

```sh
./task-cli mark-in-progress 1
./task-cli mark-done 1
```

### Listing Tasks

```sh
./task-cli list        # Lists all tasks
./task-cli list done  # Lists completed tasks
./task-cli list todo  # Lists pending tasks
./task-cli list in-progress  # Lists tasks in progress
```

## Implementation Details

- The JSON file (`tasks.json`) is created in the current directory if it doesn’t exist.
- The application uses Java's native file system operations for reading/writing tasks.
- Positional arguments are used for command-line input handling.
- Errors and edge cases are handled gracefully.

## Example JSON Structure

```json
[
  {
    "id": "1",
    "description": "Buy groceries",
    "status": "TODO",
    "createdAt": "2025-01-25T10:00:00Z",
    "updatedAt": "2025-01-25T10:00:00Z"
  }
]
```