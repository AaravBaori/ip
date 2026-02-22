package skywalker;

import java.io.File;
import java.io.IOException;

import skywalker.exception.SkywalkerException;
import skywalker.task.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSystem {
    private File f;
    private String filePath;


    public FileSystem(String filePath){
        this.f = new File(filePath);
        this.filePath = filePath;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public void addTask(Task task) throws SkywalkerException {
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.append(task.fileFormat());
            fw.close();
        } catch (IOException e) {
            throw new SkywalkerException(e.toString());
        }
    }

    public void deleteTask(int index) throws IOException, SkywalkerException{
        ArrayList<String> lines = new ArrayList<>();
        try(Scanner myReader = new Scanner(f)){
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                lines.add(data);
            }

        } catch (Exception e) {
            throw new SkywalkerException(e.getMessage());
        }

        lines.remove(index);

        try(FileWriter fw = new FileWriter(f)){
            for(String line: lines){
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            throw new SkywalkerException(e.getMessage());
        }
    }

    public void updateFile(int index, boolean status) throws SkywalkerException {
        ArrayList<Task> tasks = new ArrayList<>();

        parseFile(tasks);

        tasks.get(index).setDone(status);

        try(FileWriter fw = new FileWriter(f)){
            for(Task task: tasks){
                fw.write(task.fileFormat());
            }
        } catch (IOException e) {
            throw new SkywalkerException(e.getMessage());
        }
    }

    public void parseFile(ArrayList<Task> tasks) throws SkywalkerException {
        try(Scanner myReader = new Scanner(f)){
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();

                String[] parts = data.split("\\|", 3);
                String type = parts[0].trim();
                String status = parts[1].trim();
                String task = parts[2].trim();

                if(type.equals("T")){
                    String desc = task;
                    Task todo = new Todo(desc);
                    todo.setDone(status.equals("1"));
                    tasks.add(todo);
                } else if (type.equals("D")) {
                    String desc = task.split("\\|")[0].trim();
                    String by = task.split("\\|")[1].trim().substring(4);
                    Task deadline = new Deadline(desc, by);
                    deadline.setDone(status.equals("1"));
                    tasks.add(deadline);
                } else {
                    String desc = task.split("\\|")[0].trim();
                    String from = task.split("\\|")[1].trim().substring(6);
                    String to = task.split("\\|")[2].trim().substring(4);
                    Task event = new Event(from, to, desc);
                    event.setDone(status.equals("1"));
                    tasks.add(event);
                }

            }

        } catch (Exception e) {
            throw new SkywalkerException(e.getMessage());
        }
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
}
