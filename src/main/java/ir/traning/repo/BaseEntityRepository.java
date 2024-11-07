package ir.traning.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseEntityRepository<T> {

    void create(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(Long Id) throws SQLException;

    Optional<T> findById(Long Id) throws SQLException;

    List<T> findAll() throws SQLException;
}
