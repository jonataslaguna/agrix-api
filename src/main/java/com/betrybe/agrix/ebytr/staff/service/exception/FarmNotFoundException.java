package com.betrybe.agrix.ebytr.staff.service.exception;

/**
 * The type Farm not found exception.
 */
public class FarmNotFoundException extends NotFoundException {
  public FarmNotFoundException() {
    super("Farm not found");
  }

  /**
   * Exception for when a person is not found.
   */
  public static class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException() {
      super("Person not found");
    }

  }
}
