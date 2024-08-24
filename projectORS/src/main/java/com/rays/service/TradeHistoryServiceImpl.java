package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TradeHistoryDAOInt;
import com.rays.dto.TradeHistoryDTO;

@Service
@Transactional
public class TradeHistoryServiceImpl extends BaseServiceImpl<TradeHistoryDTO, TradeHistoryDAOInt> implements TradeHistoryServiceInt {

	@Autowired
	TradeHistoryDAOInt TradeHistoryDao;
}