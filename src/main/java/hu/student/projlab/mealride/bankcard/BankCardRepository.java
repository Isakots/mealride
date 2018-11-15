package hu.student.projlab.mealride.bankcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {
     List<BankCard> findAllByUserId(Long userId);

     BankCard getBankCardByNumber(Long number);
}
