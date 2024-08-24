package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.TranscationTypeDTO;
import com.rays.form.TranscationTypeForm;
import com.rays.service.TranscationTypeServiceInt;

@RestController
@RequestMapping(value = "TranscationType")
public class TranscationTypeCtl extends BaseCtl<TranscationTypeForm, TranscationTypeDTO, TranscationTypeServiceInt> {

	@Autowired
	private TranscationTypeServiceInt TranscationTypeService;

	@GetMapping("/preload")
	public ORSResponse preload() {
		System.out.println("inside preload");
		ORSResponse res = new ORSResponse(true);
		TranscationTypeDTO dto = new TranscationTypeDTO();
		List<DropdownList> list = TranscationTypeService.search(dto, userContext);
		res.addResult("TranscationTypeList", list);
		return res;
	}

	@GetMapping("name/{name}")
	public ORSResponse get(@PathVariable String name) {
		ORSResponse res = new ORSResponse(true);
		TranscationTypeDTO dto = baseService.findByName(name, userContext);
		System.out.println("Product " + dto);
		if (dto != null) {
			res.addData(dto);
		} else {
			res.setSuccess(false);
			res.addMessage("Record not found");
		}
		return res;
	}

}
