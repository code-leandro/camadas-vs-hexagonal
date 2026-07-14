package com.leware.camadasvshexagonal.hexagonal.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interface package-private — só o UserJpaAdapter enxerga isso.
// O resto da aplicação (inclusive o domínio) não sabe que Spring Data existe.
interface UserSpringDataRepository extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
