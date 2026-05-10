package app.repository;

import app.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {
    Optional<ContactDetails> findByUserId(UUID userId);

}
