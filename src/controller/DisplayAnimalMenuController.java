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
import model.Dog;

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
    void onActionDisplayMainMenu (ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    //Search data from table
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

            //Compare id in the index parameter
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

    //Go through animal id and select Dog is going to reference Animal
    public Animal selectAnimal(int id){
        for (Animal dog : DataProvider.getAllAnimals()){
            if(dog.getId() == id)
                return dog;
        }
        return null;
    }

    //From data provider all filtered data
    public ObservableList<Animal> filter(String breed) {
        //Clear original filter
        if (!(DataProvider.getAllFilteredAnimalsAnimals().isEmpty()))
            DataProvider.getAllFilteredAnimalsAnimals().clear();

        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getBreed().contains(breed))
                DataProvider.getAllFilteredAnimalsAnimals().add(dog);
        }

        //Switch between filtered and original list
        if ((DataProvider.getAllFilteredAnimalsAnimals().isEmpty()))
            return DataProvider.getAllAnimals();
        else
            return DataProvider.getAllFilteredAnimalsAnimals();
    }

    @FXML
    void onActionDisplayAnimalDetailsMenu (ActionEvent event) throws IOException {

        //Transfer data between controllers
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AnimalDetailsMenu.fxml"));
        loader.load();


        //Create reference variable. Instance of animal details controller
        //ERROR: ADM controller class - linked with Animal Details Controller, reference variable error
        AnimalDetailsMenuController ADMController = loader.getController();
        ADMController.sendAnimal(animalTableView.getSelectionModel().getSelectedItem());

        //Switch to animal details screen
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();

    }


        //Initialize the controller class
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

            //table view setter  getter from Data provider
            animalTableView.setItems(DataProvider.getAllAnimals());
            //Get items from filter public ObservableList<Animal> filter
            animalTableView.setItems(filter("A"));


            //Get data from return data of the dog object
            animalIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            breedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
            lifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        //initialize update - public boolean update
        if (update(5, new Dog(55,"German Shepard", 13, "Alert", 399.99, true, "Gymnast")))
          System.out.println("Update Successful!");
        else
          System.out.println("Update Failed!");


        //Initialize remove - public boolean delete
        if (delete(3))
            System.out.println("Delete Successful!");
        else
            System.out.println("No Match!");


        //Initialize select item from a table public Animal selectAnimal
            animalTableView.getSelectionModel().select(selectAnimal(3));

        }
    }

