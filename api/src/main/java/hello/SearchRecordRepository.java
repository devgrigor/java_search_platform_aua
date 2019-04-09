package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


import hello.SearchRecordRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SearchRecordRepositoryRepository
// CRUD refers Create, Read, Update, Delete

public interface SearchRecordRepository extends CrudRepository<SearchRecord, Integer> {

	@Query(value = "select * from search_record where search_record.keyword = :keyword", nativeQuery=true)
    public Collection<SearchRecord> findByKeyword(@Param("keyword") String keyword);

}