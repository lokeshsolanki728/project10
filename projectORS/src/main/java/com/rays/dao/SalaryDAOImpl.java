
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
import com.rays.dto.StatusDTO;
import com.rays.dto.SalaryDTO;

@Repository
public class SalaryDAOImpl extends BaseDAOImpl<SalaryDTO> implements SalaryDAOInt {

	@Override
	public Class<SalaryDTO> getDTOClass() {
		return SalaryDTO.class;
	}

	@Autowired
	StatusDAOInt StatusDao;

	@Override
	protected void populate(SalaryDTO dto, UserContext userContext) {
		if (dto.getStatusId() != null && dto.getStatusId() > 0) {
			StatusDTO StatusDto = StatusDao.findByPK(dto.getStatusId(), userContext);
			dto.setStatus(StatusDto.getName());
			//System.out.println(dto.getStatusName() + "PriorityName-------");
		}

	}

	@Override
	protected List<Predicate> getWhereClause(SalaryDTO dto, CriteriaBuilder builder, Root<SalaryDTO> qRoot) {
		// Create where conditions
		List<Predicate> whereCondition = new ArrayList<Predicate>();
                
		if (dto.getId() != null && dto.getId() > 0) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isZeroNumber(dto.getAmount())) {
			whereCondition.add(builder.equal(qRoot.get("amount"), dto.getAmount()));
		}

		if (isNotNull(dto.getAppliedDate())) {
			// Assuming "date" field is of type java.util.Date or java.sql.Date
			Date searchDate = dto.getAppliedDate();

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
			Predicate datePredicate = builder.between(qRoot.get("appliedDate"), startDate, endDate);
			whereCondition.add(datePredicate);
		}

		if (!isZeroNumber(dto.getStatusId())) {

			whereCondition.add(builder.equal(qRoot.get("statusId"), dto.getStatusId()));
		}

		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}

		if (!isEmptyString(dto.getEmployeeName())) {

			whereCondition.add(builder.like(qRoot.get("employeeName"), dto.getEmployeeName() + "%"));
		}

		return whereCondition;
	}

}
