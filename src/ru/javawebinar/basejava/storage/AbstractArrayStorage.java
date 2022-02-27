package ru.javawebinar.basejava.storage;

import java.util.Arrays;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int size = 0;

  public int doSize() {
    return size;
  }

  public void doClear() {
    Arrays.fill(storage, 0, size, null);
    size = 0;
  }

  public Resume doGet(int index) {
    return storage[index];
  }

  public void doDelete(int index) {
    fillDeletedElement(index);
    storage[size - 1] = null;
    size--;
  }

  public void doSave(Resume r, int index) {
    if (size == STORAGE_LIMIT) {
      throw new StorageException("Storage overflow", r.getUuid());
    }
    insertElement(r, index);
    size++;
  }

  public void doUpdate(Resume r, int index) {
    storage[index] = r;
  }

  /**
   * @return array, contains only Resumes in storage (without null)
   */
  public Resume[] doGetAll() {
    return Arrays.copyOfRange(storage, 0, size);
  }

  protected abstract int getIndex(String uuid);

  protected abstract void fillDeletedElement(int index);

  protected abstract void insertElement(Resume r, int index);
}