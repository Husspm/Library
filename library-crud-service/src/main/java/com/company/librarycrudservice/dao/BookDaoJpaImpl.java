package com.company.librarycrudservice.dao;

import com.company.librarycrudservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDaoJpaImpl extends JpaRepository<Book, Integer> {
}
