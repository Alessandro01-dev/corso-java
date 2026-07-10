package test.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import test.user.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	@Query("SELECT DISTINCT u FROM UserEntity u LEFT JOIN FETCH u.orders")
    List<UserEntity> findAllWithOrders();

}
