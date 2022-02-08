import java.util.Arrays;
import java.util.Objects;

/*
 * Array based storage for Resumes
 */
public class ArrayStorage {

  int size = 0;
  Resume[] storage = new Resume[10000];


  void clear() {
    for (int i = 0; i < storage.length; i++) {
      if (storage[i] == null) {
        break;
      }
      storage[i] = null;
      size = 0;
    }
  }

  void save(Resume r) {
    var checkRepeat = Arrays.stream(storage, 0, size)
        .anyMatch(resume -> resume.uuid.equals(r.uuid));
    if (checkRepeat) {
      return;
    }
    if (size < storage.length) {
      storage[size] = r;
    } else {
      var newStorage = new Resume[storage.length + 1];
      newStorage = Arrays.copyOf(storage, storage.length);
      newStorage[newStorage.length - 1] = r;
      storage = newStorage;
    }
    size++;
  }

  Resume get(String uuid) {
    if (size == 0) {
      return null;
    }
    return Arrays.stream(storage)
        .filter(resume -> resume != null && resume.uuid.equals(uuid))
        .findFirst().orElse(null);
  }

  void delete(String uuid) {
    if (size == 0) {
      return;
    }
    var index = (int) Arrays.stream(storage)
        .takeWhile(resume -> !resume.uuid.equals(uuid))
        .count();

    if (storage[index + 1] == null) {
      storage[index] = null;
    } else {
      var fistPartArray = new Resume[index + 1];
      var secondPartArray = new Resume[storage.length - (index + 1)];
      var newStorage = new Resume[storage.length - 1];

      System.arraycopy(storage, 0, fistPartArray, 0, index + 1);
      System.arraycopy(storage, index + 1, secondPartArray, 0, secondPartArray.length);
      System.arraycopy(fistPartArray, 0, newStorage, 0, fistPartArray.length);
      System.arraycopy(secondPartArray, 0, newStorage, fistPartArray.length - 1,
          secondPartArray.length);
      storage = newStorage;
    }
    size--;
  }

  /*
   * @return array, contains only Resumes in storage (without null)
   */
  Resume[] getAll() {
    return Arrays.stream(storage)
        .takeWhile(Objects::nonNull)
        .toArray(Resume[]::new);
  }

  int size() {
    return size;
  }
}
