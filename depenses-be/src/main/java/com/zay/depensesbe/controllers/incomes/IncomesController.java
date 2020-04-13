package com.zay.depensesbe.controllers.incomes;

import com.zay.depensesbe.data.income.Income;
import com.zay.depensesbe.dto.requests.incomes.CreateNewIncome;
import com.zay.depensesbe.dto.requests.incomes.FindIncomesRequest;
import com.zay.depensesbe.services.incomes.IncomesService;
import com.zay.depensesbe.utlis.Constants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.BASE_URI + "/incomes")
@CrossOrigin
public class IncomesController {
    final static Logger LOGGER = Logger.getLogger(IncomesController.class);

    @Autowired
    private IncomesService incomesService;

    @PostMapping("/find")
    ResponseEntity<Income> findByUser(@RequestBody FindIncomesRequest findIncomesRequest) {
        try {
            List<Income> incomes = this.incomesService.findByUserAndEffectDateBetween(findIncomesRequest.getUserId(),
                    findIncomesRequest.getFromDate(),
                    findIncomesRequest.getToDate());
            return new ResponseEntity(incomes, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/new")
    ResponseEntity findByUser(@RequestBody CreateNewIncome createNewIncome) {
        try {
             this.incomesService.createNewIncome(createNewIncome);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
