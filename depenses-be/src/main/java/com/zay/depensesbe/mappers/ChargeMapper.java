package com.zay.depensesbe.mappers;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.ChargeType;
import com.zay.depensesbe.data.charge.OneTimeCharge;
import com.zay.depensesbe.data.charge.PeriodicCharge;
import com.zay.depensesbe.dto.requests.charges.CreateChargeRequest;
import com.zay.depensesbe.dto.responses.charges.ChargeDto;

import java.util.Optional;

public class ChargeMapper {


    public static Charge map(CreateChargeRequest request, Optional<ChargeCategory> category, Optional<User> user) {
        Charge charge = null;
        if(request.getType().equals(ChargeType.ONE_TIME)){
            charge = new  OneTimeCharge();
            ((OneTimeCharge) charge).setEffectDate(request.getEffectDate());
        } else if (request.getType().equals(ChargeType.PERIODIC)){
            charge = new  PeriodicCharge();
            ((PeriodicCharge) charge).setActive(request.isActive());
            ((PeriodicCharge) charge).setEndDate(request.getEndDate());
            ((PeriodicCharge) charge).setStartDate(request.getStartDate());
            ((PeriodicCharge) charge).setPeriod(request.getPeriod());
        }

        charge.setLabel(request.getLabel());
        charge.setAmount(request.getAmount());
        charge.setCategory(category.orElse(null));
        charge.setUser(user.orElse(null));
        charge.setDescription(request.getDescription());

        return charge;
    }

    public static ChargeDto map(Charge charge)  {
        ChargeDto dto = new ChargeDto();
        if(charge instanceof PeriodicCharge){
            dto.setType(ChargeType.PERIODIC);
            dto.setActive(((PeriodicCharge) charge).isActive());
            dto.setPeriod(((PeriodicCharge) charge).getPeriod());
            dto.setStartDate(((PeriodicCharge) charge).getStartDate());
            dto.setEndDate(((PeriodicCharge) charge).getEndDate());
        } else if (charge instanceof OneTimeCharge){
            dto.setType(ChargeType.ONE_TIME);
            dto.setEffectDate(((OneTimeCharge) charge).getEffectDate());
        } else {
            return null;
        }

        dto.setAmount(charge.getAmount());
        dto.setCategory(charge.getCategory());
        dto.setUserId(charge.getUser().getId());
        dto.setId(charge.getId());
        dto.setLabel(charge.getLabel());

        return dto;

    }
}
