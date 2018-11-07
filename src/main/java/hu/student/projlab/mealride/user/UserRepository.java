package hu.student.projlab.mealride.user;

import hu.student.projlab.mealride.bankcard.BankCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Query(value="SELECT u.restaurant_id FROM User u WHERE u.user_id = :userid ", nativeQuery =  true)
    Long findRestaurantIdByUserId(@Param("userid")Long userid);

}
