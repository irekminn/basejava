package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<T> implements Storage {

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
    var key = getKey(r.getUuid());
    if (key == null) {
      throw new NotExistStorageException(r.getUuid());
    }
    doUpdate(r, key);
  }

  @Override
  public void save(Resume r) {
    doSave(r);
  }

  @Override
  public void delete(String uuid) {
    var key = getKey(uuid);
    if (key == null) {
      throw new NotExistStorageException(uuid);
    }
    doDelete(key);
  }

  @Override
  public Resume get(String uuid) {
    var key = getKey(uuid);
    if (key == null) {
      throw new NotExistStorageException(uuid);
    }
    return doGet(uuid);
  }

  @Override
  public Resume[] getAll() {
    return doGetAll();
  }

  protected abstract int doSize();

  protected abstract void doClear();

  protected abstract Resume doGet(String uuid);

  protected abstract Resume[] doGetAll();

  protected abstract void doDelete(T key);

  protected abstract void doSave(Resume r);

  protected abstract T getKey(String uuid);

  protected abstract void doUpdate(Resume r, T key);
}
