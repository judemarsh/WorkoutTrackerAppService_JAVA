package com.cts.iiht.fsd.workouttracker.workoutactive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.iiht.fsd.workouttracker.framework.entity.WorkoutActive;

@Repository
public interface WorkoutActiveRepository extends JpaRepository<WorkoutActive, Long>{
	
	@Query("SELECT p FROM WorkoutActive p WHERE workout_id = :workoutId and status = true")
    public List<WorkoutActive> findByWorkoutId(@Param("workoutId") Long workoutId);
	
	@Query("SELECT count(*) FROM WorkoutActive p WHERE workout_id = :workoutId and status = true")
	public Object[] hasActiveWorkout(@Param("workoutId") Long workoutId);
	
	/*@Query("SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND DATE_FORMAT(START_DATE,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y') and DATE_FORMAT(END_DATE,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y')")*/
	/*@Query("SELECT SUM(TIME_TO_SEC(TIMEDIFF(endTime,startTime)))/60 FROM WorkoutActive WHERE status = true AND DATE_FORMAT(startDate,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y') and DATE_FORMAT(endDate,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y')")*/
	@Query(value = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND DATE_FORMAT(START_DATE,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y') and DATE_FORMAT(END_DATE,'%m-%d-%y') = DATE_FORMAT(NOW(),'%m-%d-%y')", nativeQuery = true)
	public Object[] getTotalWorkoutTimeOfDay();
	
	/*SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND START_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND END_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE();*/
	/*@Query("SELECT SUM(TIME_TO_SEC(TIMEDIFF(endTime,startTime)))/60 FROM WorkoutActive WHERE status = true AND startDate BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND endDate BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE()")*/
	@Query(value = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND START_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND END_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE()", nativeQuery = true)
	public Object[] getTotalWorkoutTimeOfWeek();
	
	/*SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND START_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND END_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE();*/
	/*@Query("SELECT SUM(TIME_TO_SEC(TIMEDIFF(endTime,startTime)))/60 FROM workoutActive WHERE status = true AND startDate BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND endDate BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE()")*/
	@Query(value = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(END_TIME,START_TIME)))/60 FROM WORKOUT_ACTIVE WHERE STATUS = 0 AND START_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND END_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE()", nativeQuery = true)
	public Object[] getTotalWorkoutTimeOfMonth();
	
	/*SELECT DAYNAME(WA.START_DATE), SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND WA.START_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND WA.END_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() group by DAYNAME(WA.START_DATE);*/
	/*@Query("SELECT DAYNAME(WA.startDate), SUM(TIME_TO_SEC(TIMEDIFF(WA.endTime,WA.startTime)))/60*wc.caloriesBurnPerMin FROM WorkoutActive WA JOIN WorkoutCollection WC WHERE WA.status = true AND WA.startDate BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND WA.endDate BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() group by DAYNAME(WA.startDate)")*/
	@Query(value = "SELECT DAYNAME(WA.START_DATE), SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND WA.START_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() AND WA.END_DATE BETWEEN CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY AND CURDATE() group by DAYNAME(WA.START_DATE)", nativeQuery = true)
	public List<Object[]> getCaloriesBurntByDaysInWeek();
	
	/*SELECT CEIL((DATEDIFF(WA.START_DATE,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7), WA.START_DATE , SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND WA.START_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND WA.END_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() group by CEIL((DATEDIFF(WA.START_DATE,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7);*/
	/*@Query("SELECT CEIL((DATEDIFF(WA.startDate,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7), WA.startDate, SUM(TIME_TO_SEC(TIMEDIFF(WA.endTime,WA.startTime)))/60 * wc.caloriesBurnPerMin FROM WorkoutActive WA JOIN WorkoutCollection WC ON WA.workout_id = WC.workout_id WHERE WA.status = true AND WA.startDate BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND WA.endDate BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() group by CEIL((DATEDIFF(WA.startDate,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7)")*/
	@Query(value = "SELECT CEIL((DATEDIFF(WA.START_DATE,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7), SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND WA.START_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() AND WA.END_DATE BETWEEN CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY AND CURDATE() group by CEIL((DATEDIFF(WA.START_DATE,CURDATE() - INTERVAL DAYOFMONTH(CURDATE() - interval 1 day) DAY)+1)/7)", nativeQuery = true)
	public List<Object[]> getCaloriesBurntByWeeksInMonth();
	
	/*SELECT MONTHNAME(WA.START_DATE), SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND YEAR(CURDATE()) = YEAR(WA.START_DATE) AND YEAR(CURDATE()) = YEAR(WA.END_DATE) GROUP BY MONTH(WA.START_DATE);*/
	/*@Query("SELECT MONTHNAME(WA.startDate), SUM(TIME_TO_SEC(TIMEDIFF(WA.endTime,WA.startTime)))/60*wc.caloriesBurnPerMin FROM WorkoutActive WA JOIN WorkoutCollection WC WHERE WA.status = true AND YEAR(CURDATE()) = YEAR(WA.startDate) AND YEAR(CURDATE()) = YEAR(WA.endDate) GROUP BY MONTH(WA.startDate)")*/
	@Query(value ="SELECT MONTHNAME(WA.START_DATE), SUM(TIME_TO_SEC(TIMEDIFF(WA.END_TIME,WA.START_TIME)))/60*wc.calories_burn_per_min FROM WORKOUT_ACTIVE WA JOIN workout_collection WC ON WA.workout_id = WC.workout_id WHERE WA.STATUS = 0 AND YEAR(CURDATE()) = YEAR(WA.START_DATE) AND YEAR(CURDATE()) = YEAR(WA.END_DATE) GROUP BY MONTH(WA.START_DATE)", nativeQuery = true)
	public List<Object[]> getCaloriesBurntByMonthsInYear();
}
