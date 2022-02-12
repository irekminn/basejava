package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.stream.IntStream;

/*
 * Array based storage for Resumes
 */
public class ArrayStorage {

  private static final int STORAGE_LIMIT = 10000;
  private int size = 0;
  private final Resume[] storage = new Resume[STORAGE_LIMIT];


  public void clear() {
    if (size == 0) {
      return;
    }
    IntStream.range(0, size).forEach(i -> storage[i] = null);
    size = 0;
  }

  public void save(final Resume r) {
    if (r == null) {
      System.out.println("Resume for save isNull");
      return;
    }

    if (size == 0) {
      storage[0] = r;
      size++;
      return;
    }

    if (getIndex(r.getUuid()) != -1) {
      System.out.println("Duplicate resume! Resume uuid: " + r.getUuid() + " already exists");
      return;
    }

    if (size < storage.length) {
      storage[size] = r;
      size++;
    } else {
      System.out.println("Storage is full. Saving a new resume with uuid: "
          + r.getUuid() + " is not possible");
    }
  }

  public Resume get(final String uuid) {
    if (size == 0) {
      return null;
    }

    var index = getIndex(uuid);
    if (index == -1) {
      printErrorMessage(uuid);
      return null;
    }

    return storage[index];
  }

  public void delete(final String uuid) {
    if (size == 0) {
      return;
    }

    var index = getIndex(uuid);
    if (index == -1) {
      printErrorMessage(uuid);
      return;
    }

    if (storage[index + 1] == null) {
      storage[index] = null;
    } else {
      storage[index] = storage[size - 1];
      storage[size - 1] = null;
    }
    size--;
  }

  /*
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] getAll() {
    if (size == 0) {
      return new Resume[0];
    }
    return Arrays.stream(storage, 0, size).toArray(Resume[]::new);
  }

  public int size() {
    return size;
  }

  public void update(Resume r) {
    if (r == null) {
      System.out.println("Resume for update isNull");
      return;
    }
    var index = getIndex(r.getUuid());
    if (index == -1) {
      printErrorMessage(r.getUuid());
      return;
    }
    storage[index] = r;
  }

  private void printErrorMessage(String uuid) {
    System.out.println("Resume with uuid: " + uuid + " not found in storage");
  }

  private int getIndex(String uuid) {
    for (int i = 0; i < size; i++) {
      if (storage[i].getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }
}
