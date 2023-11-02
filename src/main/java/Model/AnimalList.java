package Model;

import Model.Animal.Cat;
import Model.Animal.Dog;
import Model.Animal.Hamster;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AnimalList <Animal> {
    private List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }


    public boolean removeAnimal(Animal animal){
        return animals.remove(animal);
    }


    public List<Animal> getAnimals(){
        return animals;
    }


    public List<Animal> getCats(){
        return animals.stream().filter(x -> x instanceof Cat).toList();
    }


    public List<Animal> getDogs(){
        return animals.stream().filter(x -> x instanceof Dog).toList();
    }


    public List<Animal> getHamsters(){
        return animals.stream().filter(x -> x instanceof Hamster).toList();
    }

    public Cat findCat(String name){
        List<Cat> cats = (List<Cat>) this.getCats();
        Cat cat = null;

        try {
            cat = cats.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            cat = null;
        }
        return cat;
    }

    public Dog findDog(String name){
        List<Dog> dogs = (List<Dog>) this.getDogs();
        Dog dog = null;

        try {
            dog = dogs.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            dog = null;
        }
        return dog;
    }

    public Hamster findHamster(String name){
        List<Hamster> hamsters = (List<Hamster>) this.getDogs();
        Hamster hamster = null;

        try {
            hamster = hamsters.stream().filter(c -> c.getName().equals(name)).findFirst().get();
        }
        catch (NoSuchElementException ex){
            hamster = null;
        }
        return hamster;
    }

}