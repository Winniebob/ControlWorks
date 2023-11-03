package Ui;

import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.Hamster;
import Model.AnimalCounter;
import Model.AnimalList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AnimalController {
    private final AnimalList<Object> animalList = new AnimalList<>();
    private final View view = new View();

    // главное меню
    private final Map<String, String> menuMain = new HashMap<String, String>() {{
        put("1", "Add animal");
        put("2", "Add command");
        put("3", "Display list");
        put("4", "Show commands");
        put("5", "Show number of animals");
        put("0", "Exit");
    }};
    private final Map<String, String> menuAnimal = new HashMap<>() {{
        put("1", "Cat");
        put("2", "Dog");
        put("3", "Hamster");
        put("0", "Cancel");
    }};
    private final Map<String, String> menuChoise = new HashMap<>() {{
        put("1", "Yes");
        put("0", "No");
    }};
    private enum ANIMALS {CAT, DOG, HAMSTER};

    public void Run() throws Exception {
        String menu;
        do {
            menu = getOperation();
            switch (menu) {
                case "11" -> addAnimal(ANIMALS.CAT);
                case "12" -> addAnimal(ANIMALS.DOG);
                case "13" -> addAnimal(ANIMALS.HAMSTER);
                case "21" -> addCommand(ANIMALS.CAT);
                case "22" -> addCommand(ANIMALS.DOG);
                case "23" -> addCommand(ANIMALS.HAMSTER);
                case "31" -> showAnimals(ANIMALS.CAT);
                case "32" -> showAnimals(ANIMALS.DOG);
                case "33" -> showAnimals(ANIMALS.HAMSTER);
                case "41" -> showCommands(ANIMALS.CAT);
                case "42" -> showCommands(ANIMALS.DOG);
                case "43" -> showCommands(ANIMALS.HAMSTER);
                case "5" -> showCountAnimals();
            }
        } while (!(menu.isEmpty() || menu.equals("0")));
    }

    private void showCountAnimals() throws Exception {
        try (AnimalCounter counter = new AnimalCounter()) {
            Logger.getAnonymousLogger().info(counter.getCount().toString());
        }
    }


    private void showCommands(ANIMALS animal) {
        String name = view.getString("Animal name: ");
        Object o = null;
        switch (animal) {
            case CAT -> o = animalList.findCat(name);
            case DOG -> o = animalList.findDog(name);
            case HAMSTER -> o = animalList.findHamster(name);
        }
        if (o == null) {
            Logger.getAnonymousLogger().info("Animal not found");
            return;
        }
        List<String> commands = null;
        switch (animal) {
            case CAT -> commands = ((Cat) o).getCommandList();
            case DOG -> commands = ((Dog) o).getCommandList();
            case HAMSTER -> commands = ((Hamster) o).getCommandList();
        }
        StringBuilder strCommands = new StringBuilder();
        for (String c : commands) {
            strCommands.append(c).append(", ");
        }
        Logger.getAnonymousLogger().info(strCommands.toString());
    }


    private void showAnimals(ANIMALS animal) {
        List<Object> animals = null;

        switch (animal) {
            case CAT -> animals = animalList.getCats();
            case DOG -> animals = animalList.getDogs();
            case HAMSTER -> animals = animalList.getHamsters();
        }
        Logger logger = Logger.getAnonymousLogger();
        for (Object o : animals) {
            logger.info(o.toString());
        }
    }

    private void addCommand(ANIMALS animal) {
        String name = view.getString("Animal name: ");
        Object oAnimal  = null;
        switch (animal ) {
            case CAT -> oAnimal  = animalList.findCat(name);
            case DOG -> oAnimal  = animalList.findDog(name);
            case HAMSTER -> oAnimal  = animalList.findHamster(name);
        }
        if (animal == null) {
            Logger.getAnonymousLogger().info("No such animal found");
        } else {
            String command = view.getString("New command: ");
            switch (animal) {
                case CAT -> ((Cat) oAnimal ).addCommand(command);
                case DOG -> ((Dog) oAnimal ).addCommand(command);
                case HAMSTER -> ((Hamster) oAnimal ).addCommand(command);
            }
        }
    }

    private void addAnimal(ANIMALS animal) throws Exception {
        try (AnimalCounter counter = new AnimalCounter()) {
            counter.add();
        }
        String name = view.getString("Animal name: ");
        String color = view.getString("Color: ");
        String date = view.getString("Аge: ");

        List<String> commands = new ArrayList<>();
        System.out.println("Add commands?");
        String menu = view.menuShow(menuChoise);
        while (menu.equals("1")) {
            String command = view.getString("command: ");
            commands.add(command);
            System.out.println("Continue?");
            menu = view.menuShow(menuChoise);
        }

        switch (animal) {
            case CAT -> animalList.addAnimal(new Cat(name, color, date, commands));
            case DOG -> animalList.addAnimal(new Dog(name, color, date, commands));
            case HAMSTER -> animalList.addAnimal(new Hamster(name, color, date, commands));
        }
    }

    private String getOperation() {
        String menu = view.menuShow(menuMain);
        if (!menu.isEmpty() && !menu.equals("0") && !menu.equals("5")) {
            menu += view.menuShow(menuAnimal);
        }
        return menu;
    }
}
