package hu.student.projlab.mealride.bankcard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankCardRepository extends CrudRepository<BankCard, Long> {
    public List<BankCard> findByUserId(Long userId);
}
