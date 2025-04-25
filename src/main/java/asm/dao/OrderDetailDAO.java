package asm.dao;

import org.springframework.data.jpa.repository.JpaRepository;




public interface OrderDetailDAO extends JpaRepository<asm.entity.OrderDetail, Long>{
}