package com.wartal;

import com.wartal.entity.TrendHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lwartalski on 11/10/16.
 */
public interface TrendHistoryRepository extends JpaRepository<TrendHistoryEntity, Long> {
}
