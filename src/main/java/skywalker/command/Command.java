package skywalker.command;

import skywalker.filesystem.FileSystem;

/**
 * Represents an abstract command that can be executed by the Skywalker chatbot.
 * All specific command types extend this class and implement {@link #execute(FileSystem)}.
 */
public abstract class Command {

    /**
     * Executes the command using the provided file system for persistence.
     *
     * @param fileSystem The file system instance used to read and write task data.
     * @throws Exception If an error occurs during execution.
     */
    public abstract void execute(FileSystem fileSystem) throws Exception;
}