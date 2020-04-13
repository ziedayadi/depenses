package com.zay.depensesbe.controllers.charts;

import com.zay.depensesbe.dto.requests.charges.FindChargeRequest;
import com.zay.depensesbe.dto.responses.charges.ChargeDto;
import com.zay.depensesbe.dto.responses.charts.CategoryChartData;
import com.zay.depensesbe.services.charges.ChargeService;
import com.zay.depensesbe.utlis.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(Constants.BASE_URI + "/charts")
@CrossOrigin
public class DashboardChartsController {

    @Autowired
    private ChargeService chargeService;

    @PostMapping("/categories")
    public ResponseEntity getCatData(@RequestBody FindChargeRequest request) {
        try {
            List<CategoryChartData> categoryChartDataList = new ArrayList<>();

            List<ChargeDto> charges = this.chargeService.search(
                    request.getUserId(),
                    request.getStartDate(),
                    request.getEndDate(),
                    request.getCategories());

            List<String> categoriesLabels = charges.stream().map(c -> c.getCategory().getLabel()).distinct().collect(Collectors.toList());

            categoriesLabels.forEach(c -> {
                Double catAmount = charges.stream().filter(ch -> ch.getCategory().getLabel().equals(c))
                        .mapToDouble(ch -> ch.getAmount())
                        .sum();
                CategoryChartData categoryChartData = new CategoryChartData();
                categoryChartData.setCatLabel(c);
                categoryChartData.setCatAmount(catAmount);

                categoryChartDataList.add(categoryChartData);
            });
            return new ResponseEntity(categoryChartDataList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
