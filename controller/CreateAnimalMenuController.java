package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

import java.io.IOException;
import java.net.URL;
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
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveAnimal(ActionEvent event) throws IOException {
        int id = Integer.parseInt(animalIdTxt.getText());
        String breed = breedTxt.getText();
        int lifespan = Integer.parseInt(lifespanTxt.getText());
        String behavior = behaviorTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        boolean vaccinated;
        String special = null;

        if(vacYesRBtn.isSelected())
            vaccinated = true;
        else
            vaccinated = false;

        DataProvider.addAnimal(new Dog(id, breed, lifespan, behavior, price, vaccinated, special));

        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
