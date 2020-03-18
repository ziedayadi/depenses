package com.zay.depensesbe.controllers.charges;


import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.dto.requests.charges.CreateChargeRequest;
import com.zay.depensesbe.dto.requests.charges.FindChargeRequest;
import com.zay.depensesbe.services.charges.ChargeService;
import com.zay.depensesbe.utlis.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(Constants.BASE_URI + "/charges")
@CrossOrigin
public class ChargesController {

    @Autowired
    private ChargeService chargeService;

    @PostMapping("/all")
    ResponseEntity<Collection<Charge>> findAll(@RequestBody FindChargeRequest request){
        return  new ResponseEntity(this.chargeService.findAll(request.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/new")
    ResponseEntity<Charge> createCharge(@RequestBody CreateChargeRequest request){
        return new ResponseEntity<>(this.chargeService.createCharge(request), HttpStatus.OK);
    }
}
