package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

  protected static final int STORAGE_LIMIT = 10000;
  protected int size = 0;
  protected final Resume[] storage = new Resume[STORAGE_LIMIT];

  public int size() {
    return size;
  }
}
