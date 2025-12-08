package com.imad.smartSummarize.domain.repository;

import com.imad.smartSummarize.domain.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummaryRepository extends JpaRepository<Summary,Long> {

}

