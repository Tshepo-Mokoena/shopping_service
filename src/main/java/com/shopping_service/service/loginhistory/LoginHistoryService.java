package com.shopping_service.service.loginhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.shopping_service.persistence.loginhistory.LoginHistory;
import com.shopping_service.persistence.loginhistory.LoginHistoryRepository;

@Service
public class LoginHistoryService implements ILoginHistoryService {
	
	@Autowired
	private LoginHistoryRepository loginHistoryRepository;

	@Override
	public LoginHistory save(LoginHistory loginHistory) {
		return loginHistoryRepository.save(loginHistory);
	}

	@Override
	public Page<LoginHistory> findAll(int page, int pageSize) {	
		return loginHistoryRepository.findAll(PageRequest.of(page, pageSize));
	}

}
