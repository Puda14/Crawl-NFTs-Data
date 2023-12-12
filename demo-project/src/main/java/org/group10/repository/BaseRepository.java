package org.group10.repository;

import java.util.List;

public interface BaseRepository<T,ID> {
    List<T> findAll();
     List<T> saveAll(List<T> entities);

}
