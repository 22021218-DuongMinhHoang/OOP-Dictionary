package com.example.hello.game;

public class Crop {
  private CropType type;
  private int days;
  private boolean watered;

  public Crop(CropType type) {
    this.type = type;
    watered = false;
    days = 0;
  }

  public void water() {
    watered = true;
  }

  public void nextDay() {
    if (watered) {
      days++;
      watered = false;
    } else {
      watered = false;
      days = -1;
    }
  }

  public int getDays() {
    return days;
  }

  public boolean isWatered() {
    return watered;
  }

  public CropType getType() {
    return type;
  }

  public int getStatue() {
    if (days < 0) {
      return -1;
    } else if (days <= CropsInfo.getCropTime(type)) {
      return 0;
    } else {
      return 1;
    }
  }

  public String toString() {
    return "crop++";
  }
}
