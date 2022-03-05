package ru.javawebinar.basejava.storage;

import java.util.List;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage<Storage extends List<Resume>> extends AbstractStorage<Integer> {

  private final Storage storage;

  protected ListStorage(Storage storage) {
    this.storage = storage;
  }

  @Override
  protected int doSize() {
    return storage.size();
  }

  @Override
  public void doClear() {
    storage.clear();
  }

  @Override
  protected Resume doGet(Integer key) {
    return storage.get(key);
  }

  @Override
  protected void doDelete(Integer key) {
    storage.remove(storage.get(key));
  }

  @Override
  protected void doSave(Resume r) {
    storage.add(r);
  }

  @Override
  protected void doUpdate(Resume r, Integer key) {
    storage.set(key, r);
  }

  @Override
  protected Integer getIndex(String uuid) {
    var index = storage.indexOf(new Resume(uuid));
    if (index == -1) {
      return null;
    }
    return index;
  }

  @Override
  public Resume[] doGetAll() {
    return storage.toArray(Resume[]::new);
  }

}
