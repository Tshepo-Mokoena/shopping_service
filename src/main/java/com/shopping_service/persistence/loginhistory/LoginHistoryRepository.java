package com.shopping_service.persistence.loginhistory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends PagingAndSortingRepository<LoginHistory, Long>, CrudRepository<LoginHistory, Long>{	

}
