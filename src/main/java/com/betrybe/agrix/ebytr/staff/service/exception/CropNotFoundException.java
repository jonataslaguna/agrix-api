package com.betrybe.agrix.ebytr.staff.service.exception;

/**
 * The type Crop not found exception.
 */
public class CropNotFoundException extends NotFoundException {
  public CropNotFoundException() {
    super("Crop not found");
  }
}
