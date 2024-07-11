package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.repository.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
List<Books> findAllBySerialNoIn(List<Integer>serialNo);
public Books findBySerialNo(int serialNo);

}
