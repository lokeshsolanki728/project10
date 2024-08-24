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
import com.rays.dto.AssetTrackingDTO;
import com.rays.validation.ValidAlphabetic;
import com.rays.validation.ValidDate;
import com.rays.validation.ValidDouble;
import com.rays.validation.ValidLong;

public class AssetTrackingForm extends BaseForm {

	//@Pattern(regexp = "^[A-Za-z]+([ '-][A-Za-z]+)*$", message = "Name contains alphabets only")
//	@Pattern(regexp = "^[A-Z][a-z]+ [A-Z][a-z]+$", message = "invalid name")
//	@Size(max = 20, message = "this field is 20 characters only")
//	@NotEmpty(message = "Please enter name")
	//@ValidAlphabetic
	//private Long assetId;

	@NotEmpty(message = "Please enter latitude")
	 @Pattern( regexp = "^-?([1-8]?\\d(\\.\\d{1,6})?|90(\\.0{1,6})?)$", message = "Invalid latitude. Must be between -90 and 90")
	private String latitude;

	@NotEmpty(message = "Please enter longitude")
	@Pattern( regexp = "^-?(1[0-7]\\d|[1-9]\\d|0)(\\.\\d{1,6})?$|^180(\\.0{1,6})?$",message = "Invalid longitude. Must be between -180 and 180")
    private String longitude;

	@NotNull(message = "Please enter Trackingdate")
	@ValidDate(message = "Invalid date format or value")
	private String trackingDate;

	private String assetIdName;

	 @NotEmpty(message = "Please enter AssetId")
	@ValidLong(message = "Invalid input for asset id", allowEmpty = true)
	@Min(value = 1, message = "AssetId should be greater than 0")
	private String assetId;


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getTrackingDate() {
		return trackingDate;
	}


	public void setTrackingDate(String trackingDate) {
		this.trackingDate = trackingDate;
	}


	public String getAssetIdName() {
		return assetIdName;
	}


	public void setAssetIdName(String assetIdName) {
		this.assetIdName = assetIdName;
	}


	public String getAssetId() {
		return assetId;
	}


	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}


	@Override
	public BaseDTO getDto() {
		AssetTrackingDTO dto = initDTO(new AssetTrackingDTO());

		//dto.setName(name);
		if (trackingDate != null && !trackingDate.isEmpty()) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date parsedDate = dateFormat.parse(trackingDate);
				dto.setTrackingDate(parsedDate);
			} catch (ParseException e) {
				// Handle parse exception if needed
				e.printStackTrace();
			}
		}

		if (latitude != null && !latitude.isEmpty()) {
			dto.setLatitude(Double.parseDouble(latitude));
		}

		if (longitude != null && !longitude.isEmpty()) {
			dto.setLongitude(Double.parseDouble(longitude));
		}

		if (assetId!= null && !assetId.isEmpty()) {
			dto.setAssetId(Long.valueOf(assetId));
		}	

		dto.setAssetIdName(assetIdName);

		return dto;
	}
}