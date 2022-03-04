package ru.javawebinar.basejava.storage;

import java.util.Map;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage<String> {

  protected Map<String, Resume> storage;

  @Override
  protected int doSize() {
    return storage.size();
  }

  @Override
  protected void doClear() {
    if (!storage.isEmpty()) {
      storage.clear();
    }
  }

  @Override
  protected Resume doGet(String uuid) {
    return storage.get(getKey(uuid));
  }

  @Override
  protected Resume[] doGetAll() {
    return storage.values().toArray(Resume[]::new);
  }

  @Override
  protected void doDelete(String uuid) {
    storage.remove(getKey(uuid));
  }

  @Override
  protected void doSave(Resume r) {
    if (getKey(r.getUuid()) != null) {
      throw new ExistStorageException(r.getUuid());
    }
    storage.put(r.getUuid(), r);
  }

  @Override
  protected String getKey(String uuid) {
    var key = storage.get(uuid);
    if (key == null) {
      throw new NotExistStorageException(uuid);
    }
    return uuid;
  }

  @Override
  protected void doUpdate(Resume r, String uuid) {
    if(uuid.equals(getKey(uuid))) {
      storage.put(uuid, r);
    }
  }
}
