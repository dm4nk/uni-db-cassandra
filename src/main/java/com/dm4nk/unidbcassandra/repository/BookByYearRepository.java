package com.dm4nk.unidbcassandra.repository;

import com.dm4nk.unidbcassandra.model.BookByYear;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookByYearRepository extends CassandraRepository<BookByYear, UUID> {

    @Query("SELECT * FROM bookbyyear WHERE year = ?0")
    List<BookByYear> findAllByYear(int year);
}
