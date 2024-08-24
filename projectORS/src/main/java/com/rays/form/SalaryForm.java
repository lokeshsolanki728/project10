
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
import com.rays.dto.SalaryDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidLong;

public class SalaryForm extends BaseForm {

	@NotNull(message = "Please enter amount")
	@Pattern(regexp = "^(?:[1-9]|[1-9][0-9]|[1-4][0-9]{2}|1000000)$", message = "Amount must be a number between 1 and 1000000")
	//@ValidLong(message="Invalid amount value")
	private String amount;

	@NotNull(message = "Please enter Applied Date")
	@ValidDate(message = "Invalid date format or value")
	private String appliedDate;

	//@NotEmpty(message = "Please enter employee name")
	@Size(max = 20, message = "empolyee name must be up to 20 characters")
	//@Pattern(regexp = "^[A-Z][a-zA-Z]{0,19}$", message = "Invalid employee name")
	@ValidAlphabetic
	private String employeeName;

	private String status;

	@NotEmpty(message = "Please enter statusId")
	@ValidLong(message = "Invalid input for status id", allowEmpty = true)
	@Min(value = 1, message = "statusId should be greater than 0")
	private String statusId;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	@Override
	public BaseDTO getDto() {
		SalaryDTO dto = initDTO(new SalaryDTO());

		if (amount != null && !amount.isEmpty()) {
			dto.setAmount(Long.valueOf(amount)); // Convert String to Long
		}
		if (appliedDate != null && !appliedDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(appliedDate);
				dto.setAppliedDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}
		

		if (statusId != null && !statusId.isEmpty()) {
			try {
				dto.setStatusId(Long.valueOf(statusId)); // Convert String to Long
			} catch (NumberFormatException e) {
				// Handle conversion error if productId is not a valid Long
				e.printStackTrace();
			}
		}
		dto.setStatus(status);

		dto.setEmployeeName(employeeName);

		return dto;
	}

}
