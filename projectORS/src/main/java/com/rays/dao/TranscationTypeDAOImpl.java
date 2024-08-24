package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.TranscationTypeDTO;

@Repository
public class TranscationTypeDAOImpl extends BaseDAOImpl<TranscationTypeDTO> implements TranscationTypeDAOInt {

	@Override
	public Class<TranscationTypeDTO> getDTOClass() {
		return TranscationTypeDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(TranscationTypeDTO dto, CriteriaBuilder builder, Root<TranscationTypeDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getTranscationTypeName())) {

			whereCondition.add(builder.like(qRoot.get("transcationTypeName"), dto.getTranscationTypeName() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {

			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isZeroNumber(dto.getId())) {

			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		return whereCondition;
	}

}