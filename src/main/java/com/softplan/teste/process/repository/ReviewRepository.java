package com.softplan.teste.process.repository;

import com.softplan.teste.process.model.OpinionReviewCount;
import com.softplan.teste.process.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT NEW com.softplan.teste.process.model.OpinionReviewCount(r.opinion.id, count(r.id)) FROM Review r WHERE r.process.id in :processIds GROUP BY r.opinion.id")
    List<OpinionReviewCount> countByProcessIdInGroupByOpinionId(@Param("processIds") List<Long> processIds);

    @Query("SELECT NEW com.softplan.teste.process.model.OpinionReviewCount(r.opinion.id, count(r.id)) FROM Review r WHERE r.process.id = :processId GROUP BY r.opinion.id")
    List<OpinionReviewCount> countByProcessIdGroupByOpinionId(@Param("processId") Long processId);

    @Query("SELECT r FROM Review r where r.user.id = :userId and r.process.id in :processIds")
    List<Review> findByUserIdAndProcessIdIn(@Param("userId") Long userId, @Param("processIds") List<Long> processIds);

    @Query("SELECT r FROM Review r where r.user.id = :userId and r.process.id = :processId")
    Review findByUserIdAndProcessId(@Param("userId") Long userId, @Param("processId") Long processId);

    @Query("SELECT COUNT(r.id) from Review r where r.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("SELECT r.process.id FROM Review r WHERE r.user.id = :userId")
    Page<Long> findReviewedProcessIdsByUserId(@Param("userId") Long userId, Pageable pageable);
}