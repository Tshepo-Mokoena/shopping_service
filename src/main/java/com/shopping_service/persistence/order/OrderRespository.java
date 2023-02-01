package com.shopping_service.persistence.order;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopping_service.persistence.user.User;

@Repository
public interface OrderRespository extends PagingAndSortingRepository<Order, Long>, CrudRepository<Order, Long> {
	
	/**
	 * @author Tshepo W Mokoena
	 * @apiNote finds order by user
	 * @param user
	 * @param pageable
	 * @return Page<Order>
	 */
	Page<Order> findByUser(User user, Pageable pageable);

	/**
	 * @author Tshepo W Mokoena
	 * @apiNote updates order total
	 * @param total
	 * @param id
	 */
	@Modifying
	@Query("update Order set total = :total where id = :id")
	void updateTotal(@Param("total") BigDecimal total, @Param("id") Long id);

}
