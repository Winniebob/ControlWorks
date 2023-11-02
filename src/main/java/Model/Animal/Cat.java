package Model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cat implements Animal {
    private String name;
    private String color;
    private List<String> commands;
    private String age;


    public Cat() {
        this("", "", "", new ArrayList<>());
    }

    public Cat(String name, String color, String age, List<String> commands) {
        this.name = name;
        this.color = color;
        this.commands = commands;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void addCommand(String newCommand) {
        if (commands.stream().filter(x -> x.equals(newCommand)).findFirst().isEmpty()) {
            return;
        }

        commands.add(newCommand);
    }


    @Override
    public void removeCommand(String command) {
        commands.remove(command);
    }

    @Override
    public List<String> getCommandList() {
        return commands;
    }

    @Override
    public String getColor() {
        return this.color;
    }


    @Override
    public int getCommandCount() {
        return commands.size();
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Catx ").append(name).append(" ").append(color).append(" - ").append(age);

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(name, cat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}