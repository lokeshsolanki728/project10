
package com.rays.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.TradeHistoryDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class TradeHistoryForm extends BaseForm {

	@NotNull(message = "Please enter userId")
	@Pattern(regexp = "^[a-zA-Z0-9]{5,15}$", message = "userId must be a alphanumeric between 5 and 15")
	private String userId;

	/*
	 * @NotNull(message = "Please enter Analysis Price") // @Pattern(regexp =
	 * "^(?:[1-9]|[1-9][0-9]|[1-4][0-9]{2}|1000000)$", message = //
	 * "currentValue must be a number between 1 and 1000000")
	 * // @ValidLong(message="Invalid currentValue value") // @Pattern(regexp =
	 * "^^\\d+(\\.\\d{1,2})?$", message = "Invalid price")
	 * 
	 * @ValidDouble private String AnalysisPrice;
	 */

	@NotNull(message = "Please enter Start Date")
	@ValidDate(message = "Invalid date format or value")
	private String startDate;
	
	@NotNull(message = "Please enter End Date")
	@ValidDate(message = "Invalid date format or value")
	private String endDate;

	// @NotEmpty(message = "Please enter TradeHistory name")
	// @Size(max = 30, message = "TradeHistory name must be up to 30 characters")
	// @Pattern(regexp = "^[A-Za-z0-9\\s\\-\\_]+$", message = "Invalid TradeHistory
	// name")
	// private String TradeHistoryName;

	private String transcationType ;

	@NotEmpty(message = "Please enter transcationTypeId")
	@ValidLong(message = "Invalid input for transcationType id", allowEmpty = true)
	@Min(value = 1, message = "transcationId should be greater than 0")
	private String transcationTypeId;

	
	
		public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getTranscationType() {
		return transcationType;
	}



	public void setTranscationType(String transcationType) {
		this.transcationType = transcationType;
	}



	


		public String getTranscationTypeId() {
		return transcationTypeId;
	}



	public void setTranscationTypeId(String transcationTypeId) {
		this.transcationTypeId = transcationTypeId;
	}



		@Override
	public BaseDTO getDto() {
		TradeHistoryDTO dto = initDTO(new TradeHistoryDTO());

		/*
		 * if (AnalysisPrice != null && !AnalysisPrice.isEmpty()) {
		 * dto.setAnalysisPrice(Double.valueOf(AnalysisPrice)); // Convert String to
		 * Long }
		 * 
		 * if (quantity != null && !quantity.isEmpty()) {
		 * dto.setQuantity(Long.valueOf(quantity)); // Convert String to Long }
		 */
		if (startDate != null && !startDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(startDate);
				dto.setStartDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}
		if (endDate != null && !endDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(endDate);
				dto.setEndDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (transcationTypeId != null && !transcationTypeId.isEmpty()) {
			try {
				dto.setTranscationTypeId(Long.valueOf(transcationTypeId)); // Convert String to Long
			} catch (NumberFormatException e) {
				// Handle conversion error if productId is not a valid Long
				e.printStackTrace();
			}
		}
		dto.setTranscationType(transcationType);
		dto.setUserId(userId);

	

		return dto;
	}

}
