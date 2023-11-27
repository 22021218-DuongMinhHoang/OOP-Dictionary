package com.example.hello.game;

import java.util.Hashtable;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Inventory {
  private static Inventory instance;
  private Map<CropType, IntegerProperty> seeds;
  private Map<CropType, IntegerProperty> storage;
  private IntegerProperty money;

  private Inventory() {
    seeds = new Hashtable<>();
    storage = new Hashtable<>();
    seeds.put(CropType.PARSNIP, new SimpleIntegerProperty(5));
    seeds.put(CropType.MELON, new SimpleIntegerProperty(0));
    seeds.put(CropType.PUMPKIN, new SimpleIntegerProperty(0));
    seeds.put(CropType.STARFRUIT, new SimpleIntegerProperty(0));
    seeds.put(CropType.SWEETGEMBERRY, new SimpleIntegerProperty(0));
    storage.put(CropType.PARSNIP, new SimpleIntegerProperty(0));
    storage.put(CropType.MELON, new SimpleIntegerProperty(0));
    storage.put(CropType.PUMPKIN, new SimpleIntegerProperty(0));
    storage.put(CropType.STARFRUIT, new SimpleIntegerProperty(0));
    storage.put(CropType.SWEETGEMBERRY, new SimpleIntegerProperty(0));
    money = new SimpleIntegerProperty(1000);
  }

  public static Inventory getInstance() {
    if (instance == null) {
      instance = new Inventory();
    }
    return instance;
  }

  public boolean takeSeed(CropType type) {
    if (seeds.get(type).get() > 0) {
      seeds.get(type).set(seeds.get(type).get() - 1);
      return true;
    }
    return false;
  }

  public void buySeeds(CropType type, int number) {
    if (money.get() >= CropsInfo.getCropBuyPrice(type) * number) {
      money.set(money.get() - CropsInfo.getCropBuyPrice(type) * number);
      seeds.get(type).set(seeds.get(type).get() + number);
    }
  }

  public void sellCrops(CropType type, int number) {
    if (storage.get(type).get() >= number) {
      storage.get(type).set(storage.get(type).get() - number);
      money.set(money.get() + CropsInfo.getCropSellPrice(type) * number);
    }
  }

  public void harvestCrop(CropType type) {
    storage.get(type).set(storage.get(type).get() + 1);
  }

  public void harvestCrop(CropType type, int number) {
    storage.get(type).set(storage.get(type).get() + number);
  }

  public int getMoney() {
    return money.get();
  }

  public IntegerProperty getMoneyProperty() {
    return money;
  }

  public IntegerProperty getSeedsProperty(CropType type) {
    return seeds.get(type);
  }

  public IntegerProperty getStorageProperty(CropType type) {
    return storage.get(type);
  }

  public int getCropNumber(CropType type) {
    return seeds.get(type).get();
  }

  public int getCropStorageNumber(CropType type) {
    return storage.get(type).get();
  }
}
