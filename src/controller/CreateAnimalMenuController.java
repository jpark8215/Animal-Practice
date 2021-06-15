package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateAnimalMenuController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField animalIdTxt;

    @FXML
    private TextField breedTxt;

    @FXML
    private TextField lifespanTxt;

    @FXML
    private TextField behaviorTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private RadioButton vacYesRBtn;

    @FXML
    private ToggleGroup vacTG;

    @FXML
    private RadioButton vacNoRBtn;

    @FXML
    void onActionDisplayMainMenu(ActionEvent event) throws IOException {
        //Confirmation when clicked back button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values, do you want to continue?");

        //Confirmation enum array
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {

            //Event handling loader
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSaveAnimal(ActionEvent event) throws IOException {

        //Exception handler try - catch when nothing is saved
        try{

            //Wrapper class get text brings string
            int id = Integer.parseInt(animalIdTxt.getText());
            String breed = breedTxt.getText();
            int lifespan = Integer.parseInt(lifespanTxt.getText());
            String behavior = behaviorTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            boolean vaccinated;
            //UI has no ability collecting this See Dog.java
            String special = null;

            if(vacYesRBtn.isSelected())
                vaccinated = true;
            else
                vaccinated = false;

            //Create new Dog object see DataProvider.java
            DataProvider.addAnimal(new Dog(id, breed, lifespan, behavior, price, vaccinated, special));


            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

        catch(NumberFormatException e){
            //Warning dialogue box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialogue");
            alert.setContentText("Please enter valid value for each text fields");
            alert.showAndWait();

            /*
            //Error dialogue box
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialogue");
            alert.setContentText("Please enter valid value for each text fields");
            alert.showAndWait();


            //Exception handling codes
            System.out.println("Please enter valid value in text fields");
            System.out.println("Exception" + e);
            System.out.println("Exception" + e.getMessage());
            */

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
