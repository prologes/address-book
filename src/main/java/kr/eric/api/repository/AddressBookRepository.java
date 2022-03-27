package kr.eric.api.repository;

import kr.eric.api.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {

    Optional<AddressBook> findFirstByIdAndUserId(Long id, String userId);
}
