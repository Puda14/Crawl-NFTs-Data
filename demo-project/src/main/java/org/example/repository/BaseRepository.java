package org.example.repository;

import org.example.model.post.Tweet;

import java.util.List;

public interface BaseRepository<T,ID> {
    List<T> findAll();
     void saveAll(Iterable<T> entities);

}
