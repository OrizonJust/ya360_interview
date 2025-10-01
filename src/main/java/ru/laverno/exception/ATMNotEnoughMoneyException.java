package ru.laverno.exception;

public class ATMNotEnoughMoneyException extends RuntimeException {
  public ATMNotEnoughMoneyException(String message) {
    super(message);
  }
}
