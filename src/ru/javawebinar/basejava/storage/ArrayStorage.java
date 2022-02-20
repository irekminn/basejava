package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based ru.javawebinar.basejava.storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

  public ArrayStorage() {
  }

  public ArrayStorage(int storageLimit) {
    super(storageLimit);
  }

  @Override
  protected void fillDeletedElement(int index) {
    storage[index] = storage[size - 1];
  }

  @Override
  protected void insertElement(Resume r, int index) {
    storage[size] = r;
  }

  @Override
  protected int getIndex(String uuid) {
    for (int i = 0; i < size; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }
}