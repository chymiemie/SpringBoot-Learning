package com.zte.chy.mongodb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.zte.chy.model.Material;

@Repository
public interface MaterialRepository extends MongoRepository<Material, String> {

	List<Material> findByBarcode(String barcode);

	Page<Material> findByBarcode(String barcode, Pageable pageable);

	List<Material> findByMaterial(String material);

	Page<Material> findByMaterial(String material, Pageable pageable);

	@Query(value = "{'material' : ?0 ,'createTime' : {$gte : ?1,$lte : ?2}}")
	Page<Material> findByMaterialAndCreateTimeRange(String materialCode, Date beginTime, Date endTime,
			Pageable pageable);

	@Query(value = "{'material' : ?0 ,'createTime' : {$gte : ?1}}")
	Page<Material> findByMaterialAndCreateTimeAfter(String materiaCode, Date beginTime, Pageable pageable);

	@Query(value = "{'material' : ?0 ,'createTime' : {$lte : ?1}}")
	Page<Material> findByMaterialAndCreateTimeBefore(String materiaCode, Date endTime, Pageable pageable);

	@Query(value = "{'material' : ?0 ,'createTime' : {$gte : ?1,$lte : ?2}}")
	List<Material> findByMaterialAndCreateTimeRange(String materialCode, Date beginTime, Date endTime);

	@Query(value = "{'material' : ?0 ,'createTime' : {$gte : ?1}}")
	List<Material> findByMaterialAndCreateTimeAfter(String materiaCode, Date beginTime);

	@Query(value = "{'material' : ?0 ,'createTime' : {$lte : ?1}}")
	List<Material> findByMaterialAndCreateTimeBefore(String materiaCode, Date endTime);
}
