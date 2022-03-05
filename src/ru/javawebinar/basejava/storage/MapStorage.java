package ru.javawebinar.basejava.storage;

import java.util.Map;
import ru.javawebinar.basejava.model.Resume;

public class MapStorage<Storage extends Map<String, Resume>> extends AbstractStorage<String> {

  private final Storage storage;

  public MapStorage(Storage storage) {
    this.storage = storage;
  }


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
    storage.put(r.getUuid(), r);
  }

  @Override
  protected void doUpdate(Resume r, String uuid) {
    if (uuid.equals(getKey(uuid))) {
      storage.put(uuid, r);
    }
  }

  @Override
  protected String getIndex(String uuid) {
    return storage.containsKey(uuid) ? uuid : null;
  }
}
