
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

import com.rays.dto.TradeHistoryDTO;
import com.rays.dto.TranscationTypeDTO;


@Repository
public class TradeHistoryDAOImpl extends BaseDAOImpl<TradeHistoryDTO> implements TradeHistoryDAOInt {

	@Override
	public Class<TradeHistoryDTO> getDTOClass() {
		return TradeHistoryDTO.class;
	}

	@Autowired
	TranscationTypeDAOInt transcationTypeDao;

	@Override
	protected void populate(TradeHistoryDTO dto, UserContext userContext) {
		if (dto.getTranscationTypeId() != null && dto.getTranscationTypeId() > 0) {
			TranscationTypeDTO transcationTypeDto = transcationTypeDao.findByPK(dto.getTranscationTypeId(), userContext);
			dto.setTranscationType(transcationTypeDto.getName());
			//System.out.println(dto.getTypeName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(TradeHistoryDTO dto, CriteriaBuilder builder, Root<TradeHistoryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();
                
		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		/*
		 * if (!isZeroNumber(dto.getAnalysisPrice())) {
		 * whereCondition.add(builder.equal(qRoot.get("AnalysisPrice"),
		 * dto.getAnalysisPrice())); }
		 */

		if (isNotNull(dto.getEndDate())) {
			// Assuming "date" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getEndDate();

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
			Predicate datePredicate = builder.between(qRoot.get("endDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}
		
		
		if (isNotNull(dto.getStartDate())) {
			// Assuming "date" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getStartDate();

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
			Predicate datePredicate = builder.between(qRoot.get("startDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getTranscationTypeId())) {

			whereCondition.add(builder.equal(qRoot.get("transcationTypeId"), dto.getTranscationTypeId()));
		}

		if (!isEmptyString(dto.getTranscationType())) {

			whereCondition.add(builder.like(qRoot.get("transcationType"), dto.getTranscationType() + "%"));
		}

		if (!isEmptyString(dto.getUserId())) {

		whereCondition.add(builder.like(qRoot.get("userId"), dto.getUserId() + "%"));
	}
//		
		/*
		 * if (!isZeroNumber(dto.getStockSymbol())) {
		 * whereCondition.add(builder.equal(qRoot.get("quantity"), dto.getQuantity()));
		 * }
		 */
		return whereCondition;
	}

}
