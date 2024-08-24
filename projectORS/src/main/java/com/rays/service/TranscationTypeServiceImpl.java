package com.rays.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.TranscationTypeDAOInt;
import com.rays.dto.TranscationTypeDTO;

@Service
@Transactional
public class TranscationTypeServiceImpl extends BaseServiceImpl<TranscationTypeDTO, TranscationTypeDAOInt> implements TranscationTypeServiceInt {

	private static Logger log = LoggerFactory.getLogger(TranscationTypeServiceImpl.class);

	@Transactional(readOnly = true)
	public TranscationTypeDTO findByName(String name, UserContext userContext) {
		return baseDao.findByUniqueKey("name", name, userContext);
	}
}
