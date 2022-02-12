package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.stream.IntStream;

public abstract class AbstractArrayStorage implements Storage {

  protected static final int STORAGE_LIMIT = 10000;
  protected int size = 0;
  protected final Resume[] storage = new Resume[STORAGE_LIMIT];

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

    var index = getIndex(r.getUuid());
    if (index >= 0) {
      System.out.println("Duplicate resume! Resume uuid: " + r.getUuid() + " already exists");
      return;
    }

    if (size == storage.length) {
      System.out.println("Storage is full. Saving a new resume with uuid: "
          + r.getUuid() + " is not possible");
      return;
    }
    addElement(r, index);
    size++;
  }

  public int size() {
    return size;
  }

  public Resume get(final String uuid) {
    if (size == 0) {
      return null;
    }

    var index = getIndex(uuid);
    if (index < 0) {
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
    if (index < 0) {
      printErrorMessage(uuid);
      return;
    }

    if (index + 1 < storage.length && storage[index + 1] == null) {
      storage[index] = null;
      size--;
      return;
    }
    delElement(index);
    storage[size - 1] = null;
    size--;
  }

  public Resume[] getAll() {
    if (size == 0) {
      return new Resume[0];
    }
    return Arrays.stream(storage, 0, size).toArray(Resume[]::new);
  }

  public void update(final Resume r) {
    if (r == null) {
      System.out.println("Resume for update isNull");
      return;
    }
    var index = getIndex(r.getUuid());
    if (index < 0) {
      printErrorMessage(r.getUuid());
      return;
    }
    storage[index] = r;
  }

  protected abstract void addElement(Resume r, int index);

  protected abstract int getIndex(String uuid);

  protected abstract void delElement(int index);

  private void printErrorMessage(final String uuid) {
    System.out.println("Resume with uuid: " + uuid + " not found in storage");
  }

}
