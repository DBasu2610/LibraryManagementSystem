package lms.borrow_service.repository;

import lms.borrow_service.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

}