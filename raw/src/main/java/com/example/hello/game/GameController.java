package com.example.hello.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.hello.data.Word;
import com.example.hello.management.SceneSwitch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GameController implements Initializable {

  @FXML
  private Pane levelPane;

  @FXML
  private Text question;

  @FXML
  private Button choice1;

  @FXML
  private Button choice2;

  @FXML
  private Button choice3;

  @FXML
  private Button choice4;

  @FXML
  private Pane mainPane;

  @FXML
  private GridPane plantArea;

  @FXML
  private Label money;

  @FXML
  private Text parnipNumber;

  @FXML
  private Text parnipStorage;

  @FXML
  private Text melonNumber;

  @FXML
  private Text melonStorage;

  @FXML
  private Text pumpkinNumber;

  @FXML
  private Text pumpkinStorage;

  @FXML
  private Text starfruitNumber;

  @FXML
  private Text starfruitStorage;

  @FXML
  private Text sweetgemNumber;

  @FXML
  private Text sweetgemStorage;

  @FXML
  private Pane shopPane;

  Slot slot1;

  public void start(CropType type, Slot slot) {
    levelPane.setVisible(true);
    Level level = new Level();
    question.setText(level.getQuestion());
    Word[] word = level.getOptions();
    choice1.setText(word[0].getWord());
    choice2.setText(word[1].getWord());
    choice3.setText(word[2].getWord());
    choice4.setText(word[3].getWord());

    choice1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (0 == level.getRightAnswer()) {
          levelPane.setVisible(false);
          Inventory.getInstance().harvestCrop(type);
          slot.harvestCrop();
        }
      }
    });

    choice2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (1 == level.getRightAnswer()) {
          levelPane.setVisible(false);
          Inventory.getInstance().harvestCrop(type);
          slot.harvestCrop();
        }
      }
    });

    choice3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (2 == level.getRightAnswer()) {
          levelPane.setVisible(false);
          Inventory.getInstance().harvestCrop(type);
          slot.harvestCrop();
        }
      }
    });

    choice4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (3 == level.getRightAnswer()) {
          levelPane.setVisible(false);
          Inventory.getInstance().harvestCrop(type);
          slot.harvestCrop();
        }
      }
    });
  }

  public void nextDay() {
    slot1.nextDay();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    levelPane.setVisible(false);

    slot1 = new Slot();
    plantArea.add(slot1.getActions(), 0, 0);
    slot1.getHarvestItem().setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        start(slot1.getCrop().getType(), slot1);
      }
    });

    money.textProperty().bind(Inventory.getInstance().getMoneyProperty().asString());

    parnipNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.PARNIP).asString());
    parnipStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.PARNIP).asString());

    melonNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.MELON).asString());
    melonStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.MELON).asString());

    pumpkinNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.PUMPKIN).asString());
    pumpkinStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.PUMPKIN).asString());

    starfruitNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.STARFRUIT).asString());
    starfruitStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.STARFRUIT).asString());

    sweetgemNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.SWEETGEMBERRY).asString());
    sweetgemStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.SWEETGEMBERRY).asString());
  }

}
