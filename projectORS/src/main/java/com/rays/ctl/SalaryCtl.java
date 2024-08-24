
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
import com.rays.dto.SalaryDTO;
import com.rays.form.SalaryForm;
import com.rays.service.StatusServiceInt;
import com.rays.service.SalaryServiceInt;

@RestController
@RequestMapping(value = "Salary")
public class SalaryCtl extends BaseCtl<SalaryForm, SalaryDTO, SalaryServiceInt> {

	@Autowired
	StatusServiceInt statusService;

	@Autowired
	SalaryServiceInt salaryService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload Amit");
		ORSResponse res = new ORSResponse(true);
		StatusDTO dto = new StatusDTO();
		List<DropdownList> list = statusService.search(dto, userContext);
		res.addResult("statusList", list);
		return res;
	}

	@PostMapping
	public ResponseEntity<String> createSalary(@Valid @RequestBody SalaryForm SalaryForm) {
		// Handle the logic to save the patient
		return new ResponseEntity<>("Salary created successfully", HttpStatus.CREATED);
	}
}
