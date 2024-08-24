
package com.rays.ctl;

import java.util.List;

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
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.StatusDTO;
import com.rays.dto.TranscationTypeDTO;
import com.rays.dto.TradeHistoryDTO;
import com.rays.form.TradeHistoryForm;
import com.rays.service.StatusServiceInt;
import com.rays.service.TranscationTypeServiceInt;
import com.rays.service.TradeHistoryServiceInt;

@RestController
@RequestMapping(value = "TradeHistory")
public class TradeHistoryCtl extends BaseCtl<TradeHistoryForm, TradeHistoryDTO, TradeHistoryServiceInt> {

	@Autowired
	TranscationTypeServiceInt transcationTypeService;

	@Autowired
	TradeHistoryServiceInt TradeHistoryService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Amit");
		ORSResponse res = new ORSResponse(true);
		TranscationTypeDTO dto = new TranscationTypeDTO();
		List<DropdownList> list = transcationTypeService.search(dto, userContext);
		res.addResult("transcationTypeList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createTradeHistory(@Valid @RequestBody TradeHistoryForm TradeHistoryForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("TradeHistory created successfully", HttpStatus.CREATED);
	}
}
