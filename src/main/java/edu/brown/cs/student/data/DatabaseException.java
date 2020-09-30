package edu.brown.cs.student.data;
/**
 * Raise exception if something goes wrong accessing database.
 */
public class DatabaseException extends Exception {
  /**
   * Creates exception.
   * @param message message of exception
   */
  public DatabaseException(String message) {
    super(message);
  }
}
