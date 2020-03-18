package com.zay.depensesbe.mappers;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.ChargeType;
import com.zay.depensesbe.data.charge.OneTimeCharge;
import com.zay.depensesbe.data.charge.PeriodicCharge;
import com.zay.depensesbe.dto.requests.charges.CreateChargeRequest;

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
}
