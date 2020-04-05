package com.zay.depensesbe.controllers.charges;


import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.SaveChargeRequest;
import com.zay.depensesbe.dto.requests.charges.FindChargeRequest;
import com.zay.depensesbe.dto.responses.charges.ChargeDto;
import com.zay.depensesbe.services.charges.ChargeService;
import com.zay.depensesbe.utlis.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URI + "/charges")
@CrossOrigin
public class ChargesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargesController.class);

    @Autowired
    private ChargeService chargeService;

    @PostMapping("/all")
    ResponseEntity<Collection<ChargeDto>> findAll(@RequestBody FindChargeRequest request){
        return  new ResponseEntity(this.chargeService.findAll(request.getUserId()), HttpStatus.OK);
    }

    @PostMapping("/save")
    ResponseEntity<Charge> save(@RequestBody SaveChargeRequest request){
        return new ResponseEntity<>(this.chargeService.saveCharge(request), HttpStatus.OK);
    }

    @GetMapping("/categories")
    ResponseEntity<List<ChargeCategory>> findAllChargesCategories(){
        return new ResponseEntity<>(this.chargeService.getAllChargesCategories(), HttpStatus.OK);
    }

    @PostMapping("/search")
    ResponseEntity<Collection<ChargeDto>> search(@RequestBody FindChargeRequest request){

        if(request.getUserId() == null || request.getStartDate() == null || request.getEndDate() == null){
            return new ResponseEntity(Constants.TECH_PROBLEM_MESSAGE,HttpStatus.BAD_REQUEST);
        }
        try{
            ResponseEntity<Collection<ChargeDto>> result =   new ResponseEntity(this.chargeService.search(
                    request.getUserId(),
                    request.getStartDate(),
                    request.getEndDate()),
                    HttpStatus.OK);
            return result;
        } catch (Exception e){
            LOGGER.error("ERROR",e);
            return new ResponseEntity(Constants.TECH_PROBLEM_MESSAGE,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCharge(@RequestParam("chargeId") Long chargeId){
        try {
            this.chargeService.deleteCharge(chargeId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e ){
            LOGGER.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() , e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
