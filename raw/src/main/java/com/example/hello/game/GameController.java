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
  private Pane mainPane;

  @FXML
  private GridPane plantArea;

  @FXML
  private Label money;

  @FXML
  private Text parsnipNumber;

  @FXML
  private Text parsnipStorage;

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

  @FXML
  private GridPane levelOptions;

  @FXML
  private Text levelNumber;

  @FXML
  private Text rightAnswerNumber;

  Slot slot1;

  public void start(CropType type, Slot slot) {
    int lv = 0;
    int rightNums = 0;
    levelPane.setVisible(true);
    playing(type, slot, lv, rightNums);
  }

  private void playing(CropType type, Slot slot, int lv, int rightNums) {
    if (lv <= CropsInfo.getCropTime(type)) {
      levelNumber.setText(String.format("%d / %d", lv + 1, CropsInfo.getCropTime(type) + 1));
      rightAnswerNumber.setText(String.format("%d", rightNums));
      if (!levelOptions.getChildren().isEmpty()) {
        levelOptions.getChildren().clear();
      }
      Level level = new Level();
      question.setText(level.getQuestion());
      Word[] word = level.getOptions();
      Button[] choice = new Button[4];
      for (int i = 0; i < 4; i++) {
        choice[i] = new Button();
        choice[i].setText(word[i].getWord());
        switch (i) {
          case 0:
            choice[i].setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                if (0 == level.getRightAnswer()) {
                  playing(type, slot, lv + 1, rightNums + 1);
                } else {
                  playing(type, slot, lv + 1, rightNums);
                }
              }
            });
            break;
          case 1:
            choice[i].setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                if (1 == level.getRightAnswer()) {
                  playing(type, slot, lv + 1, rightNums + 1);
                } else {
                  playing(type, slot, lv + 1, rightNums);
                }
              }
            });
            break;
          case 2:
            choice[i].setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                if (2 == level.getRightAnswer()) {
                  playing(type, slot, lv + 1, rightNums + 1);
                } else {
                  playing(type, slot, lv + 1, rightNums);
                }
              }
            });
            break;
          case 3:
            choice[i].setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent event) {
                if (3 == level.getRightAnswer()) {
                  playing(type, slot, lv + 1, rightNums + 1);
                } else {
                  playing(type, slot, lv + 1, rightNums);
                }
              }
            });
            break;
        }
        levelOptions.add(choice[i], i % 2, i / 2);
      }
    } else {
      levelPane.setVisible(false);
      double number = 3 * ((double) rightNums / (double) (CropsInfo.getCropTime(type) + 1));
      Inventory.getInstance().harvestCrop(type, (int) number);
      slot.harvestCrop();
    }

  }

  public void nextDay() {
    slot1.nextDay();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
    levelPane.setVisible(false);
    shopPane.setVisible(false);

    slot1 = new Slot();
    plantArea.add(slot1.getActions(), 0, 0);
    slot1.getHarvestItem().setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        start(slot1.getCrop().getType(), slot1);
      }
    });

    money.textProperty().bind(Inventory.getInstance().getMoneyProperty().asString());

    parsnipNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.PARSNIP).asString());
    parsnipStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.PARSNIP).asString());

    melonNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.MELON).asString());
    melonStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.MELON).asString());

    pumpkinNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.PUMPKIN).asString());
    pumpkinStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.PUMPKIN).asString());

    starfruitNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.STARFRUIT).asString());
    starfruitStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.STARFRUIT).asString());

    sweetgemNumber.textProperty().bind(Inventory.getInstance().getSeedsProperty(CropType.SWEETGEMBERRY).asString());
    sweetgemStorage.textProperty().bind(Inventory.getInstance().getStorageProperty(CropType.SWEETGEMBERRY).asString());
  }

  public void shopping() {
    if (shopPane.getChildren().isEmpty()) {
      shopPane.setVisible(true);
      Shop shop = new Shop();
      shopPane.getChildren().add(shop.getShop());
      shop.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          shopPane.setVisible(false);
          shopPane.getChildren().clear();
        }
      });
    }
  }
}
