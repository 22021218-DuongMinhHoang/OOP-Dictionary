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

  private CustomMenuItem plantBag;
  private SplitMenuButton plant;
  private MenuItem parnip;
  private MenuItem melon;
  private MenuItem pumpkin;
  private MenuItem starfruit;
  private MenuItem sweetgemberry;

  public Slot() {
    crop = null;
    actions = new MenuButton("Slot1");

    plant = new SplitMenuButton();
    plant.setText("Seeds");

    harvest = new MenuItem("Harvest");
    harvest.setVisible(false);

    actions.getItems().add(harvest);
    actions.setText("Plant");

    plantBag = new CustomMenuItem();
    updatePlantBag();
    actions.getItems().add(plantBag);

    /** Water. */
    water = new MenuItem("Water");
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
  }

  private MenuItem newPlantItem(MenuItem pitem, CropType type) {
    String str = "%d";
    str = String.format(str, Inventory.getInstance().getCropNumber(type));
    pitem = new MenuItem(CropsInfo.getCropName(type) + " " + str);
    pitem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        if (Inventory.getInstance().takeSeed(type)) {
          crop = new Crop(type);
          actions.setText(CropsInfo.getCropName(type));
          plantBag.setVisible((crop == null));
          water.setVisible(!crop.isWatered());
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
      if (crop.getStatue() > 0) {
        harvest.setVisible(true);
      } else if (crop.getStatue() == 0) {
        water.setVisible(!crop.isWatered());
      }
    }
  }

  private void updatePlantBag() {
    if (!plant.getItems().isEmpty()) {
      plant.getItems().clear();
    }

    /** Parnip. */
    parnip = newPlantItem(parnip, CropType.PARNIP);

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
    actions.setText("Plant");
    updatePlantBag();
  }
}
