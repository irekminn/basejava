package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

  void clear();

  void save(final Resume r);

  Resume get(final String uuid);

  void delete(final String uuid);

  Resume[] getAll();

  int size();

  void update(final Resume r);
}
