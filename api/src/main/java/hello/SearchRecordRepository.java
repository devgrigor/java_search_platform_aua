package hello;

import org.springframework.data.repository.CrudRepository;

import hello.SearchRecordRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SearchRecordRepositoryRepository
// CRUD refers Create, Read, Update, Delete

public interface SearchRecordRepository extends CrudRepository<SearchRecord, Integer> {

}