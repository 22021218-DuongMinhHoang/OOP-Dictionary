package com.example.hello.game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class Slot {
  private Crop crop;
  private Button button;
  private MenuButton actions;

  private MenuItem water;
  private MenuItem harvest;
  private MenuItem destroy;

  private CustomMenuItem plantBag;
  private SplitMenuButton plant;
  private MenuItem parsnip;
  private MenuItem melon;
  private MenuItem pumpkin;
  private MenuItem starfruit;
  private MenuItem sweetgemberry;

  public Slot() {
    crop = null;
    actions = new MenuButton("");

    plant = new SplitMenuButton();
    plant.setText("Hạt giống");

    harvest = new MenuItem("Thu hoạch");
    harvest.setVisible(false);

    actions.getItems().add(harvest);

    plantBag = new CustomMenuItem();
    updatePlantBag();
    actions.getItems().add(plantBag);

    /** Water. */
    water = new MenuItem("Tưới nước");
    water.setVisible(false);
    water.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (crop != null) {
          if (!crop.isWatered()) {
            crop.water();
            water.setVisible(false);
          }
        }
      }
    });
    actions.getItems().add(water);

    /** Destroy. */
    destroy = new MenuItem("Loại bỏ");
    destroy.setVisible(false);
    destroy.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (crop != null) {
          crop = null;
          water.setVisible(false);
          harvest.setVisible(false);
          destroy.setVisible(false);
          plantBag.setVisible(true);
          String style = "";
          String path = getClass().getResource("../resources/icons/plantSlot.png").toExternalForm();
          style += "-fx-background-color: rgb(0,0,0,0);";
          style += "-fx-background-image: url(" + path + ");";
          style += "-fx-background-size: cover;";
          actions.setStyle(style);
        }
      }
    });
    actions.getItems().add(destroy);
  }

  private MenuItem newPlantItem(MenuItem pitem, CropType type) {
    pitem = new MenuItem(CropsInfo.getCropVNName(type));
    pitem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (Inventory.getInstance().takeSeed(type)) {
          crop = new Crop(type);
          plantBag.setVisible((crop == null));
          water.setVisible(!crop.isWatered());
          String style = "";
          String path = getClass().getResource(CropsInfo.getStagePath(crop.getType(), crop.getDays())).toExternalForm();
          style += "-fx-background-color: rgb(0,0,0,0);";
          style += "-fx-background-image: url(" + path + ");";
          style += "-fx-background-size: 30 40;";
          style += "-fx-background-position: center;";
          style += "-fx-background-repeat: no-repeat;";
          actions.setStyle(style);
        }
      }
    });
    plant.getItems().add(pitem);
    return pitem;
  }

  public MenuButton getActions() {
    return actions;
  }

  public MenuItem getHarvestItem() {
    return harvest;
  }

  public Crop getCrop() {
    return crop;
  }

  public void nextDay() {
    if (crop != null) {
      crop.nextDay();
      String style = "";
      String path = getClass().getResource(CropsInfo.getStagePath(crop.getType(), crop.getDays())).toExternalForm();
      style += "-fx-background-color: rgb(0,0,0,0);";
      style += "-fx-background-image: url(" + path + ");";
      style += "-fx-background-size: 30 40;";
      style += "-fx-background-position: center;";
      style += "-fx-background-repeat: no-repeat;";
      actions.setStyle(style);
      if (crop.getStatue() > 0) {
        harvest.setVisible(true);
      } else if (crop.getStatue() == 0) {
        water.setVisible(!crop.isWatered());
      } else {
        water.setVisible(false);
        destroy.setVisible(true);
        style = "";
        path = getClass().getResource("../resources/icons/Die_Crop.png").toExternalForm();
        style += "-fx-background-color: rgb(0,0,0,0);";
        style += "-fx-background-image: url(" + path + ");";
        style += "-fx-background-size: 30 40;";
        style += "-fx-background-position: center;";
        style += "-fx-background-repeat: no-repeat;";
        actions.setStyle(style);
      }

    }
  }

  private void updatePlantBag() {
    if (!plant.getItems().isEmpty()) {
      plant.getItems().clear();
    }

    /** Parsnip. */
    parsnip = newPlantItem(parsnip, CropType.PARSNIP);

    /** Melon. */
    melon = newPlantItem(melon, CropType.MELON);

    /** Pumpkin. */
    pumpkin = newPlantItem(pumpkin, CropType.PUMPKIN);

    /** Starfruit. */
    starfruit = newPlantItem(starfruit, CropType.STARFRUIT);

    /** SweetGemBerry. */
    sweetgemberry = newPlantItem(sweetgemberry, CropType.SWEETGEMBERRY);

    /** Plants. */

    plantBag.setContent(plant);
    plantBag.setVisible((crop == null));
  }

  public void harvestCrop() {
    crop = null;
    harvest.setVisible(false);

    plantBag.setVisible((crop == null));
    updatePlantBag();
  }
}
