package ru.javawebinar.basejava.storage;

public class ArrayStorageTest extends AbstractArrayStorageTest{
  private final static int CAPACITY = 3;

  public ArrayStorageTest() {
    super(new ArrayStorage(CAPACITY));
  }
}