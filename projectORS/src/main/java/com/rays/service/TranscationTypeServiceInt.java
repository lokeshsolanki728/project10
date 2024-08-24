package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.TranscationTypeDTO;

public interface TranscationTypeServiceInt extends BaseServiceInt<TranscationTypeDTO> {

	/**
	 * Finds Role by name.
	 * 
	 * @param name
	 * @return
	 */
	public TranscationTypeDTO findByName(String name, UserContext userContext);

}
