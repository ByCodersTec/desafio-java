package com.desafiodev.infrastructure.repositories.jpas;

import com.desafiodev.infrastructure.repositories.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreEntityJpaRepository extends JpaRepository<StoreEntity, String> {}
