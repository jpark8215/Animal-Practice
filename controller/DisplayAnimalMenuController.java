package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Animal;
import model.DataProvider;
import model.Dog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Animal> animalTableView;

    @FXML
    private TableColumn<Animal, Integer> animalIdCol;

    @FXML
    private TableColumn<Animal, String> breedCol;

    @FXML
    private TableColumn<Animal, Integer> lifespanCol;

    @FXML
    private TableColumn<Animal, Double> priceCol;

    @FXML
    void onActionDisplayAnimalDetailsMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/DisplayAnimalMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public boolean search(int id) {
        for (Animal dog : DataProvider.getAllAnimals()) {
            if(dog.getId() == id)
                return true;
        }
        return false;
    }

    //Update objects
    public boolean update (int id, Animal animal){
        int index = -1;
        for(Animal dog : DataProvider.getAllAnimals()){
            index++;

            if(dog.getId() == id){
                DataProvider.getAllAnimals().set(index, animal);
                return true;
            }
        }
        return false;
    }

    //Remove objects
    public boolean delete(int id){
        for(Animal dog : DataProvider.getAllAnimals()){
            if(dog.getId() == id){
                return  DataProvider.getAllAnimals().remove(dog);
            }
        }
        return false;
    }

    //Initialize the controller class
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        animalTableView.setItems(DataProvider.getAllAnimals());
//Get Id from return ID of the dog object

        //ERROR: Observable list - linked with Main
        animalIdCol.setCellValueFactory(new propertyValueFactory<>("id"));
        breedCol.setCellValueFactory(new propertyValueFactory<>("breed"));
        lifespanCol.setCellValueFactory(new propertyValueFactory<>("lifespan"));
        priceCol.setCellValueFactory(new propertyValueFactory<>("price"));

        /*
        if (update(5, new Dog(55,"German Shepard", 13, "Alert", 399.99, true, "Gymnast")))
          System.out.println("Update Successful!");
        else
          System.out.println("Update Failed!");
        */

        if (delete(3))
            System.out.println("Delete Successful!");
        else
            System.out.println("No Match!");
    }
}


