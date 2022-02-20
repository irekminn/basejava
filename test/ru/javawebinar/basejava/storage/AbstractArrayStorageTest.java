package ru.javawebinar.basejava.storage;

import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {

  private final Storage storage;

  public AbstractArrayStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void setUp() throws Exception {
    storage.clear();
    for (int i = 0; i < storage.capacity(); i++) {
      storage.save(new Resume(UUID.randomUUID().toString()));
    }
  }

  @Test
  public void size() throws Exception {
    Assert.assertEquals(storage.capacity(), storage.size());
  }

  @Test
  public void clear() throws Exception {
    storage.clear();
    Assert.assertEquals(0, storage.size());
    Assert.assertEquals(0, storage.getAll().length);
  }

  @Test
  public void update() throws Exception {
    var newResume = new Resume[storage.size()];
    var oldResume = storage.getAll();

    for (int i = 0; i < newResume.length; i++) {
      newResume[i] = new Resume(oldResume[i].getUuid());
    }

    for (int i = 0; i < storage.size(); i++) {
      storage.update(newResume[i]);
      if (!(storage.getAll()[i] == newResume[i])) {
        Assert.fail("Not update resume: " + storage.getAll()[i].getUuid());
      }

      Assert.assertEquals(newResume[i], storage.getAll()[i]);
    }

  }

  @Test(expected = NotExistStorageException.class)
  public void updateNotExist() throws Exception {
    storage.update(new Resume("dummy"));
  }

  @Test
  public void getAll() throws Exception {
    Assert.assertEquals(storage.size(), storage.getAll().length);
  }

  @Test(expected = StorageException.class)
  public void saveOverflow() throws Exception {
    var r = new Resume("dummy");
    storage.save(r);
  }

  @Test(expected = ExistStorageException.class)
  public void saveExist() throws Exception {
    var r = storage.getAll();
    for (Resume resume : r) {
      storage.save(resume);
    }
  }

  @Test
  public void save() throws Exception {
    storage.clear();
    var arrayUuid = new String[storage.capacity()];
    for (int i = 0; i < arrayUuid.length; i++) {
      arrayUuid[i] = UUID.randomUUID().toString();
      try {
        storage.save(new Resume(arrayUuid[i]));
      } catch (StorageException e) {
        Assert.fail("early overflow");
      }
    }
    Assert.assertEquals(storage.capacity(), storage.size());
  }

  @Test
  public void delete() throws Exception {
    var r = storage.getAll();
    for (int i = storage.size(); i > 0; i--) {
      storage.delete(r[i - 1].getUuid());
      Assert.assertEquals(i - 1, storage.size());
    }
    Assert.assertEquals(0, storage.getAll().length);
  }

  @Test(expected = NotExistStorageException.class)
  public void deleteNotExist() throws Exception {
    storage.delete("dummy");
  }

  @Test
  public void get() throws Exception {
    var r = storage.getAll();
    for (Resume resume : r) {
      Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }
  }

  @Test(expected = NotExistStorageException.class)
  public void getNotExist() throws Exception {
    storage.get("dummy");
  }
}