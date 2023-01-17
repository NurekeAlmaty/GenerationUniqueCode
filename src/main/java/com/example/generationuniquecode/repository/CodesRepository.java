package com.example.generationuniquecode.repository;

import com.example.generationuniquecode.entity.CodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface CodesRepository extends JpaRepository<CodesEntity, Long> {

    @Query("select count(c) from CodesEntity c where c.code= :pCode")
    Integer cntFound(String pCode);
}
