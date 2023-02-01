package com.shopping_service.service.loginhistory;

import org.springframework.data.domain.Page;

import com.shopping_service.persistence.loginhistory.LoginHistory;

public interface ILoginHistoryService {
	
	LoginHistory save(LoginHistory loginHistory);
	
	Page<LoginHistory> findAll(int page, int pageSize);

}
