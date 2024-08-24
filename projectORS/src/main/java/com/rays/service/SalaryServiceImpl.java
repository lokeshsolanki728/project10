package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SalaryDAOInt;
import com.rays.dto.SalaryDTO;

@Service
@Transactional
public class SalaryServiceImpl extends BaseServiceImpl<SalaryDTO, SalaryDAOInt> implements SalaryServiceInt {

	@Autowired
	SalaryDAOInt salaryDao;
}