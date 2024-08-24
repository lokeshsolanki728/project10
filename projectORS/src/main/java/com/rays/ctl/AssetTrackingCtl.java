package com.rays.ctl;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.AssetTrackingDTO;
import com.rays.form.AssetTrackingForm;
import com.rays.service.AssetTrackingServiceInt;

@RestController
@RequestMapping(value = "AssetTracking")
public class AssetTrackingCtl extends BaseCtl<AssetTrackingForm, AssetTrackingDTO, AssetTrackingServiceInt> {


	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Paras");
		ORSResponse res = new ORSResponse(true);
		HashMap<Integer, String> map=new HashMap<Integer, String>();
		map.put(1, "This");
		map.put(2, "That");
		res.addResult("assetIdList", map);
		return res;
	}
	
}
