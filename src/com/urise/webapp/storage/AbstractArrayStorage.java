package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

  protected static final int STORAGE_LIMIT = 10000;
  protected int size = 0;
  protected final Resume[] storage = new Resume[STORAGE_LIMIT];

  public int size() {
    return size;
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

  protected abstract void printErrorMessage(String uuid);

  protected abstract int getIndex(String uuid);

}
