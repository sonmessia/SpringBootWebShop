package asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;




public interface OrderDAO extends JpaRepository<asm.entity.Order, Long>{
}
