package com.rays.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;


import com.rays.dto.AssetTrackingDTO;

@Repository
public class AssetTrackingDAOImpl extends BaseDAOImpl<AssetTrackingDTO> implements AssetTrackingDAOInt {

	@Override
	public Class<AssetTrackingDTO> getDTOClass() {
		return AssetTrackingDTO.class;
	}

	

	@Override
	protected List<Predicate> getWhereClause(AssetTrackingDTO dto, CriteriaBuilder builder,
			Root<AssetTrackingDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<>();

		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		

		if (!isZeroNumber(dto.getLatitude())) {
			whereCondition.add(builder.equal(qRoot.get("latitude"), dto.getLatitude()));
		}

		if (!isZeroNumber(dto.getLongitude())) {
			whereCondition.add(builder.equal(qRoot.get("longitude"), dto.getLongitude()));
		}

		if (isNotNull(dto.getTrackingDate())) {
			// Assuming "dateOfPurchase" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getTrackingDate();

			// Define start and end dates for the search day
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(searchDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0); // Start of the day
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date startDate = calendar.getTime();

			calendar.set(Calendar.HOUR_OF_DAY, 23); // End of the day
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date endDate = calendar.getTime();

			// Create predicate for date range
			Predicate datePredicate = builder.between(qRoot.get("trackingDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getAssetId())) {
			whereCondition.add(builder.equal(qRoot.get("assetId"), dto.getAssetId()));
		}

		if (!isEmptyString(dto.getAssetIdName())) {
			whereCondition.add(builder.like(qRoot.get("assetIdName"), dto.getAssetIdName() + "%"));
		}

		return whereCondition;
	}

}