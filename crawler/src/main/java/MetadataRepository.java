package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Metadata;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MetadataRepository extends CrudRepository<Metadata, Integer> {
/*
    @Query(value = "select * from search_record where search_record.keyword = :keyword", nativeQuery=true)
    public Collection<SearchRecord> findByKeyword(@Param("keyword") String keyword);

*/

/*      example of insert from SO

@Transactional
@Query(value = "INSERT INTO recipes(title, description) VALUES (?1, ?2)", nativeQuery = true)
    void insertRecipes(String title, String description);

*/
}