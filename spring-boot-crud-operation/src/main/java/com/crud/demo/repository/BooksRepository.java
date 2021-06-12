package com.crud.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.crud.demo.model.Books;
public interface BooksRepository extends CrudRepository<Books, Integer>
{
}
