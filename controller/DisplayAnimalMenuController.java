package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Animal;
import model.DataProvider;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalMenuController implements Initializable {
    Stage stage;
    Parent scene;

    //Get data from Model into table
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

        //Transfer data from other controller
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/MainMenu.fxml"));
        loader.load();

        //Create reference variable
        //ERROR: ADM controller class - linked with Animal Details Controller
        AnimalDetailsMenuController.ADMController = loader.getController();
        ADMController.sendAnimal(animalTableView.getSelectionModel().getSelectedItem());

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();

        /*
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        */
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

    public Animal selectAnimal(int id){
        for (Animal dog : DataProvider.getAllAnimals()){
            if(dog.getId() == id)
                return dog;
        }
        return null;
    }

    public ObservableList<Animal> filter(String breed) {
        if (!(DataProvider.getAllFilteredAnimalsAnimals().isEmpty()))
            DataProvider.getAllFilteredAnimalsAnimals().clear();

        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getBreed().contains(breed))
                DataProvider.getAllFilteredAnimalsAnimals().add(dog);
        }


            if ((DataProvider.getAllFilteredAnimalsAnimals().isEmpty()))
                DataProvider.getAllAnimals();
            else
                return DataProvider.getAllFilteredAnimalsAnimals();

    }

        //Initialize the controller class
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            //table view setter  getter from Data provider
            animalTableView.setItems(DataProvider.getAllAnimals());
            //animalTableView.setItems(filter("A"));

            //Get data from return data of the dog object
            animalIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            breedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
            lifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        /*
        if (update(5, new Dog(55,"German Shepard", 13, "Alert", 399.99, true, "Gymnast")))
          System.out.println("Update Successful!");
        else
          System.out.println("Update Failed!");


        if (delete(3))
            System.out.println("Delete Successful!");
        else
            System.out.println("No Match!");


            //Select item from a table
            animalTableView.getSelectionModel().select(selectAnimal(3));

         */
        }
    }

