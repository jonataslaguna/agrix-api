package com.betrybe.agrix.ebytr.staff.service.exception;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFoundException extends RuntimeException {
  public PersonNotFoundException() {
    super("Person not found");
  }

}
