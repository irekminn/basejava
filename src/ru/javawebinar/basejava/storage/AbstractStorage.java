package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

  @Override
  public int size() {
    return doSize();
  }

  @Override
  public void clear() {
    if (doSize() > 0) {
      doClear();
    }
  }

  @Override
  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index < 0) {
      throw new NotExistStorageException(r.getUuid());
    }
    doUpdate(r, index);
  }

  @Override
  public void save(Resume r) {
    int index = getIndex(r.getUuid());
    if (index >= 0) {
      throw new ExistStorageException(r.getUuid());
    }
    doSave(r, index);
  }

  @Override
  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      throw new NotExistStorageException(uuid);
    }
    doDelete(index);
  }

  @Override
  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index < 0) {
      throw new NotExistStorageException(uuid);
    }
    return doGet(index);
  }

  @Override
  public Resume[] getAll() {
    return doGetAll();
  }

  protected abstract int doSize();

  protected abstract void doClear();

  protected abstract Resume doGet(int index);

  protected abstract Resume[] doGetAll();

  protected abstract void doDelete(int index);

  protected abstract void doSave(Resume r, int index);

  protected abstract int getIndex(String uuid);

  protected abstract void doUpdate(Resume r, int index);
}
