package ru.javawebinar.basejava.storage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {
  private final static int CAPACITY = 3;

  public SortedArrayStorageTest() {
    super(new SortedArrayStorage(CAPACITY));
  }
}