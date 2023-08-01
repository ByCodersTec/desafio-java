package com.desafiodev.infrastructure.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.desafiodev.infrastructure.repositories.jpas.TransactionEntityJpaRepository;
import com.desafiodev.utils.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionEntityRepositoryTest {

  @Mock private TransactionEntityJpaRepository transactionEntityJpaRepository;

  private TransactionEntityRepository transactionEntityRepository;

  @BeforeEach
  void setUp() {
    when(transactionEntityJpaRepository.save(any())).thenReturn(any());
    transactionEntityRepository = new TransactionEntityRepository(transactionEntityJpaRepository);
  }

  @Test
  void save() {
    transactionEntityRepository.save(Fixture.getTransaction());
    verify(transactionEntityJpaRepository, times(1)).save(any());
  }
}
