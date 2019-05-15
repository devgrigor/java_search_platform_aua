package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


import hello.MetaData;

// This will be AUTO IMPLEMENTED by Spring into a Bean called MetaDataRRepositoryRepository
// CRUD refers Create, Read, Update, Delete

public interface MetaDataRepository extends CrudRepository<MetaData, Integer> {

	@Query(value = "select * from meta_data where meta_data.title like %:keyword% or meta_data.description like %:keyword% group by url", nativeQuery=true)
    public Collection<MetaData> findByKeyword(@Param("keyword") String keyword);

}