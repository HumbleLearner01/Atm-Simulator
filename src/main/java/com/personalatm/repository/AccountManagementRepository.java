package com.personalatm.repository;

import com.personalatm.model.AccountManagement;
import com.personalatm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountManagementRepository extends JpaRepository<AccountManagement, Long> {
    @Query("SELECT b.balance FROM AccountManagement b WHERE b.user.userId=:userId AND b.bankName=:bankName")
    Double getAccountBalanceByUserAndBankName(@Param("userId") long userId, @Param("bankName") String bankName);

    List<AccountManagement> findByExpirationDateLessThan(Date currentDate);

    List<AccountManagement> findByBankName(String bankName);

    Optional<List<AccountManagement>> findAccountManagementsByUserUserId(long userId);

    Optional<AccountManagement> findByUserUserId(long userId);

    Optional<AccountManagement> findByAccountNumberAndBankNameLike(String accountNumber, String bankName);

    @Query("SELECT u FROM AccountManagement u WHERE u.accountNumber=:accountNumber AND u.user.password=:password AND u.bankName=:bankName")
    Optional<AccountManagement> getUserByAccountNumberAndPassword(@Param("accountNumber") String accountNumber,
                                                                  @Param("password") String password,
                                                                  @Param("bankName") String bankName);

    @Query("select a from AccountManagement a where a.accountNumber = ?1")
    Optional<AccountManagement> findByAccountNumber(String accountNumber);
}