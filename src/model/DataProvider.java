package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataProvider {
    private static ObservableList<Animal> allAnimals = FXCollections.observableArrayList();
    private static ObservableList<Animal> allFilteredAnimals = FXCollections.observableArrayList();


    //Add animals to observable list
    public static void addAnimal(Animal animal){ allAnimals.add(animal); }

    //Returning all observable animal list
    public static ObservableList<Animal> getAllAnimals(){
        return allAnimals;
    }

    //Returning all observable filtered animal list
    public static ObservableList<Animal> getAllFilteredAnimalsAnimals(){
        return allFilteredAnimals;
    }
}
