package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.AssetTrackingDAOInt;
import com.rays.dto.AssetTrackingDTO;

@Service
@Transactional
public class AssetTrackingServiceImpl extends BaseServiceImpl<AssetTrackingDTO, AssetTrackingDAOInt>
		implements AssetTrackingServiceInt {

	@Autowired
	AssetTrackingDAOInt AssetTrackingDAO;

}
