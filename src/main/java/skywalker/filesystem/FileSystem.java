package skywalker.filesystem;

import skywalker.exception.SkywalkerException;
import skywalker.task.Deadline;
import skywalker.task.Event;
import skywalker.task.Task;
import skywalker.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing of task data to and from a persistent save file.
 * Each task is stored as a single line using the format defined by {@link Task#fileFormat()}.
 */
public class FileSystem {

    /** The save file used to persist task data. */
    private File dataFile;

    /** The path to the save file. */
    private String filePath;

    /**
     * Constructs a FileSystem instance pointing to the specified file path.
     *
     * @param filePath The path to the save file to read from and write to.
     */
    public FileSystem(String filePath) {
        this.dataFile = new File(filePath);
        this.filePath = filePath;
    }
    /**
     * Appends a single task to the end of the save file.
     *
     * @param task The task to persist.
     * @throws SkywalkerException If an I/O error occurs while writing.
     */
    public void addTask(Task task) throws SkywalkerException {
        try (FileWriter fw = new FileWriter(dataFile, true)) {
            fw.append(task.fileFormat());
        } catch (IOException e) {
            throw new SkywalkerException(e.getMessage());
        }
    }

    /**
     * Deletes the task at the specified index from the save file by rewriting all other lines.
     *
     * @param index The 0-based index of the task line to remove.
     * @throws SkywalkerException If an I/O error occurs while reading or writing.
     */
    public void deleteTask(int index) throws SkywalkerException {
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(dataFile)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (Exception e) {
            throw new SkywalkerException(e.getMessage());
        }

        lines.remove(index);

        try (FileWriter fw = new FileWriter(dataFile)) {
            for (String line : lines) {
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            throw new SkywalkerException(e.getMessage());
        }
    }

    /**
     * Updates the completion status of the task at the specified index in the save file.
     * Reads all tasks, updates the target, then rewrites the entire file.
     *
     * @param index The 0-based index of the task to update.
     * @param isDone The new completion status to set.
     * @throws SkywalkerException If an I/O error occurs while reading or writing.
     */
    public void updateFile(int index, boolean isDone) throws SkywalkerException {
        ArrayList<Task> tasks = new ArrayList<>();
        parseFile(tasks);
        tasks.get(index).setDone(isDone);

        try (FileWriter fw = new FileWriter(dataFile)) {
            for (Task task : tasks) {
                fw.write(task.fileFormat());
            }
        } catch (IOException e) {
            throw new SkywalkerException(e.getMessage());
        }
    }

    /**
     * Reads the save file and populates the provided list with the parsed tasks.
     * Clears the list before populating. Silently skips parsing if the file does not exist.
     * Supports task types T (Todo), D (Deadline), and E (Event).
     *
     * @param tasks The list to populate with tasks read from the file.
     * @throws SkywalkerException If the file exists but cannot be read or parsed.
     */
    public void parseFile(ArrayList<Task> tasks) throws SkywalkerException {
        tasks.clear();
        if (!dataFile.exists()) {
            return;
        }

        try (Scanner scanner = new Scanner(dataFile)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parts = data.split("\\|", 3);
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String remainder = parts[2].trim();

                if (type.equals("T")) {
                    Task todo = new Todo(remainder);
                    todo.setDone(isDone);
                    tasks.add(todo);
                } else if (type.equals("D")) {
                    String[] deadlineParts = remainder.split("\\|");
                    String desc = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim().substring(4);
                    Task deadline = new Deadline(desc, by);
                    deadline.setDone(isDone);
                    tasks.add(deadline);
                } else if (type.equals("E")) {
                    String[] eventParts = remainder.split("\\|");
                    String desc = eventParts[0].trim();
                    String from = eventParts[1].trim().substring(6);
                    String to = eventParts[2].trim().substring(4);
                    Task event = new Event(from, to, desc);
                    event.setDone(isDone);
                    tasks.add(event);
                }
            }
        } catch (Exception e) {
            throw new SkywalkerException(e.getMessage());
        }
    }
}