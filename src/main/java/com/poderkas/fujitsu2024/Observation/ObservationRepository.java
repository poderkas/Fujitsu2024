package com.poderkas.fujitsu2024.Observation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservationRepository extends JpaRepository<Observation,Long> {
    public Observation findTopByTimestampLessThanEqualOrderByTimestampDesc(Long timestamp);

}
