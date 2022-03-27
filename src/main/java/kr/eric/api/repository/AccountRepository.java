package kr.eric.api.repository;

import kr.eric.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findFirstByUserId(String userId);

    Optional<Account> findFirstByRegNo(String regNo);
}
