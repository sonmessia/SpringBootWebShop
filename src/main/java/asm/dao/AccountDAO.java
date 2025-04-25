package asm.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import asm.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String>{
	
	@Query("select a from Account a where a.email like ?1")
	public Optional<Account> findByEmail(String email);
	
	@Query("select a from Account a where a.username like ?1 and a.password like ?2")
	public Optional<Account> checkLogin(String userName, String password);
	
	@Query("select a from Account a where a.username like ?1")
	public Optional<Account> findByUserName(String username); 
	
}
