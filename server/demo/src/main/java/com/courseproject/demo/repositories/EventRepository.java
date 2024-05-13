package com.courseproject.demo.repositories;

import com.courseproject.demo.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {
  List<Event> findByNameIgnoreCase(String name);

  List<Event> findByName(String name);

  @Override
  Optional<Event> findById(Integer integer);

    long removeById(Integer id);
}