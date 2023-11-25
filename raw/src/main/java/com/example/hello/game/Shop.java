package com.example.hello.game;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Shop {
  private GridPane shop;
  private Map<CropType, IntegerProperty> bill;

  public Shop() {
    shop = new GridPane();

    bill = new Hashtable<>();
    bill.put(CropType.PARNIP, new SimpleIntegerProperty(0));
    bill.put(CropType.MELON, new SimpleIntegerProperty(0));
    bill.put(CropType.PUMPKIN, new SimpleIntegerProperty(0));
    bill.put(CropType.STARFRUIT, new SimpleIntegerProperty(0));
    bill.put(CropType.SWEETGEMBERRY, new SimpleIntegerProperty(0));
  }

  public void addShopOptions(CropType type) {
    Text cropName = new Text(CropsInfo.getCropName(type));
    cropName.setStyle("-fx-font-size: 15");
    shop.getChildren().add(cropName);

    Button plus1 = new Button("+1");
    plus1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bill.get(type).set(bill.get(type).get() + 1);
      }
    });

    Button plus5 = new Button("+5");
    plus5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bill.get(type).set(bill.get(type).get() + 5);
      }
    });

    Button minus1 = new Button("-1");
    minus1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bill.get(type).set(bill.get(type).get() - 1);
        if (bill.get(type).get() < -Inventory.getInstance().getCropStorageNumber(type)) {
          bill.get(type).set(-Inventory.getInstance().getCropStorageNumber(type));
        }
      }
    });

    Button minus5 = new Button("-5");
    minus5.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bill.get(type).set(bill.get(type).get() - 5);
        if (bill.get(type).get() < -Inventory.getInstance().getCropStorageNumber(type)) {
          bill.get(type).set(-Inventory.getInstance().getCropStorageNumber(type));
        }
      }
    });

    Text cropNum = new Text();
    cropNum.setStyle("-fx-font-size: 15");
    cropNum.textProperty().bind(bill.get(type).asString());

    Text cropPrice = new Text();

    bill.get(type).addListener(new ChangeListener<Number>() {

      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        if (newValue.intValue() >= 0) {
          cropPrice.setText(String.format("%d", newValue.intValue() * CropsInfo.getCropBuyPrice(type)));
        } else {
          cropPrice.setText(String.format("%d", newValue.intValue() * CropsInfo.getCropSellPrice(type)));
        }
      }
    });
  }
}
