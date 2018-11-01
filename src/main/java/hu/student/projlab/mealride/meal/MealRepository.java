package hu.student.projlab.mealride.meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, String> {

}
