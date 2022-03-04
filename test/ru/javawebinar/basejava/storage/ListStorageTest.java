package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class ListStorageTest extends AbstractStorageTest {

  protected ListStorageTest(Storage storage) {
    super(storage);
  }

  @Override
  protected int doSize() {
    return storage.size();
  }

  @Override
  protected void doClear() {
storage.clear();
  }

  @Override
  protected void doSave(Resume r) {
storage.save(r);
  }

  @Override
  protected void doUpdate(Resume newResume) {
storage.update(newResume);
  }

  @Override
  protected void doGet(String dummy) {
storage.get(dummy);
  }

  @Override
  protected void doDelete(String uuid) {
storage.delete(uuid);
  }
}