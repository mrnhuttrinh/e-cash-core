package com.ecash.ecashcore.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
  @RestResource(exported = false)
  void delete(ID id);

  @RestResource(exported = false)
  void delete(T entity);

  @RestResource(exported = false)
  void delete(Iterable<? extends T> entities);

  @RestResource(exported = false)
  void deleteAll();

  @RestResource(exported = false)
  <S extends T> S save(S entity);

  @RestResource(exported = false)
  <S extends T> List<S> save(Iterable<S> entities);

  @RestResource(exported = false)
  <S extends T> S saveAndFlush(S entity);
  
  @RestResource(exported = false)
  Page<T> findAll(Pageable pageable);
}
