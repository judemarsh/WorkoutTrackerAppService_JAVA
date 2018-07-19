package com.cts.iiht.fsd.workouttracker.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutCategory;

@Repository
public interface CategoryRepository extends JpaRepository<WorkoutCategory, Long>{

}
