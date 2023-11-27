package com.example.hello.game;

enum CropType {
  PARSNIP,
  MELON,
  PUMPKIN,
  STARFRUIT,
  SWEETGEMBERRY
}

public class CropsInfo {
  public static String getCropName(CropType type) {
    String name = "";
    if (type == CropType.PARSNIP) {
      name = "Parsnip";
    } else if (type == CropType.MELON) {
      name = "Melon";
    } else if (type == CropType.PUMPKIN) {
      name = "Pumpkin";
    } else if (type == CropType.STARFRUIT) {
      name = "Starfruit";
    } else {
      name = "SweetGemBerry";
    }
    return name;
  }

  public static int getCropTime(CropType type) {
    if (type == CropType.PARSNIP) {
      return 2;
    } else if (type == CropType.MELON) {
      return 4;
    } else if (type == CropType.PUMPKIN) {
      return 6;
    } else if (type == CropType.STARFRUIT) {
      return 8;
    } else {
      return 10;
    }
  }

  public static int getCropBuyPrice(CropType type) {
    if (type == CropType.PARSNIP) {
      return 0;
    } else if (type == CropType.MELON) {
      return 80;
    } else if (type == CropType.PUMPKIN) {
      return 100;
    } else if (type == CropType.STARFRUIT) {
      return 400;
    } else {
      return 1000;
    }
  }

  public static int getCropSellPrice(CropType type) {
    if (type == CropType.PARSNIP) {
      return 35;
    } else if (type == CropType.MELON) {
      return 250;
    } else if (type == CropType.PUMPKIN) {
      return 320;
    } else if (type == CropType.STARFRUIT) {
      return 750;
    } else {
      return 3000;
    }
  }
}
