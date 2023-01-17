package org.cyfwms.common.login.repository;

import org.cyfwms.common.login.entity.BlacklistedTokensId;
import org.cyfwms.common.login.entity.BlacklistedTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistedTokensRepository extends JpaRepository<BlacklistedTokens, BlacklistedTokensId> {
    Optional<BlacklistedTokens> findByUsernameAndJwtToken(String username, String jwtToken);
}
