package ru.javawebinar.basejava.storage;

import java.util.List;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage<Integer> {

  protected List<Resume> storage;

  @Override
  protected int doSize() {
    return storage.size();
  }

  @Override
  public void doClear() {
    storage.clear();
  }

  @Override
  protected Resume doGet(String uuid) {
    var key = getKey(uuid);
    return storage.get(key);
  }

  @Override
  protected void doDelete(Integer key) {
    storage.remove(key);
  }

  @Override
  protected void doSave(Resume r) {
    if (getKey(r.getUuid()) != null) {
      throw new ExistStorageException(r.getUuid());
    }
    storage.add(r);
  }

  @Override
  protected Integer getKey(String uuid) {
    return storage.indexOf(uuid);
  }

  @Override
  protected void doUpdate(Resume r, Integer key) {
    storage.set(key, r);
  }

  @Override
  public Resume[] doGetAll() {
    return storage.toArray(Resume[]::new);
  }

}
