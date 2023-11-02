package Model.Animal;

import java.util.List;

public interface Animal {
    String getName();

    void setAge(String age);

    String getAge();

    void setName(String name);

    void addCommand(String newCommand);

    void removeCommand(String command);

    List<String> getCommandList();

    String getColor();

    int getCommandCount();

    void setColor(String color);
}
