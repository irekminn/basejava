package ru.javawebinar.basejava.storage;

import java.util.List;
import java.util.Optional;
import ru.javawebinar.basejava.model.Resume;

public class ListStorage extends AbstractStorage {

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
  protected Resume doGet(int index) {
    return storage.get(index);
  }

  @Override
  protected void doDelete(int index) {
    storage.remove(index);
  }

  @Override
  protected void doSave(Resume r, int index) {
    storage.add(r);
  }

  @Override
  protected void doUpdate(Resume r, int index) {
    storage.set(index, r);
  }

  @Override
  public Resume[] doGetAll() {
    return storage.toArray(Resume[]::new);
  }

  protected int getIndex(String uuid) {
    return Optional.of((int) storage.stream()
            .takeWhile(resume -> !resume.getUuid().equals(uuid))
            .count())
        .orElse(-1);
  }
}
