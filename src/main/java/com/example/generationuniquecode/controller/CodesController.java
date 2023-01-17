package com.example.generationuniquecode.controller;

import com.example.generationuniquecode.dto.CodesDTO;
import com.example.generationuniquecode.service.CodesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/codes")
public class CodesController {
    @Autowired
    private CodesService codesService;

    @GetMapping("/get_new_code")
    public CodesDTO getNewCode(){
        CodesDTO codes = codesService.getNewCode();
        System.out.println("codes : "+codes.getCode());
        return codes;
    }
}
