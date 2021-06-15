package JPFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        //Objects for observable list
        Dog dog1 = new Dog(1,"Siberian Husky", 15, "Crafty", 599.99, true, "Whistles");
        Dog dog2 = new Dog(2,"Alaskan Malamute", 12, "Energetic", 699.99, true, "Climbs");
        Dog dog3 = new Dog(3,"Dalmatian", 13, "Energetic", 799.99, true, "Gymnast");
        Dog dog4 = new Dog(4,"Dogo Argentino", 12, "Protective", 899.99, true, "Whistles");
        Dog dog5 = new Dog(5,"Anatonian Shepard", 12, "Intelligent", 999.99, true, "Gymnast");

        DataProvider.addAnimal(dog1);
        DataProvider.addAnimal(dog2);
        DataProvider.addAnimal(dog3);
        DataProvider.addAnimal(dog4);
        DataProvider.addAnimal(dog5);

        launch(args);

    }

}
