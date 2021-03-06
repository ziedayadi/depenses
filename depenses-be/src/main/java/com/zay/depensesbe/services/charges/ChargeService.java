package com.zay.depensesbe.services.charges;

import com.zay.depensesbe.data.User;
import com.zay.depensesbe.data.charge.Charge;
import com.zay.depensesbe.data.charge.PeriodicCharge;
import com.zay.depensesbe.data.period.PeriodTime;
import com.zay.depensesbe.data.ref.ChargeCategory;
import com.zay.depensesbe.dto.requests.charges.SaveChargeRequest;
import com.zay.depensesbe.dto.responses.charges.ChargeDto;
import com.zay.depensesbe.mappers.ChargeMapper;
import com.zay.depensesbe.repositories.UsersJpaRepository;
import com.zay.depensesbe.repositories.charge.ChargesCategoriesJpaRepository;
import com.zay.depensesbe.repositories.charge.ChargesJpaRepositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChargeService {

    private static final Logger LOGGER = Logger.getLogger(ChargeService.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");


    @Autowired
    private ChargesJpaRepositories chargesJpaRepositories;
    @Autowired
    private ChargesCategoriesJpaRepository categoryJpaRepository;
    @Autowired
    private UsersJpaRepository usersJpaRepository;

    private static void incrementDate(Calendar cal, PeriodTime periodTime) {
        switch (periodTime) {
            case MONTH:
                cal.add(Calendar.MONTH, 1);
                break;
            case YEAR:
                cal.add(Calendar.YEAR, 1);
                break;
            case WEEK:
                cal.add(Calendar.DAY_OF_YEAR, 7);
                break;

        }


    }

    public Collection<ChargeDto> findAll(Long userId) {
        return this.chargesJpaRepositories.findByUserId(userId)
                .stream()
                .map(ChargeMapper::map)
                .collect(Collectors.toList());
    }

    public Charge saveCharge(SaveChargeRequest request) {
        Optional<ChargeCategory> category = this.categoryJpaRepository.findById(request.getCategoryId());
        Optional<User> user = this.usersJpaRepository.findById(request.getUserId());
        Charge charge = ChargeMapper.map(request, category, user);
        return this.chargesJpaRepositories.save(charge);
    }

    public List<ChargeCategory> getAllChargesCategories() {
        return this.categoryJpaRepository.findAllByOrderByLabel();
    }

    public List<ChargeDto> search(Long userId, Date startDate, Date endDate, List<String> categories) {
        return this.findByPeriod(userId, startDate, endDate, categories);
    }

    private List<ChargeDto> findByPeriod(Long userId, Date startDate, Date endDate, List<String> categories) {

        List<ChargeDto> dtos = new ArrayList<>();

        //1. Find OneTime Charges
        dtos.addAll(this.findOneTimeCharges(userId, startDate, endDate, categories));

        //2. Find Periodic charges
        dtos.addAll(this.findPeriodicCharges(userId, startDate, endDate, categories));

        return dtos;

    }

    private List<ChargeDto> findOneTimeCharges(Long userId, Date startDate, Date endDate, List<String> categories) {
        LOGGER.info("Searching ONE_TIME charges for user between " + DATE_FORMAT.format(startDate) + " And " + DATE_FORMAT.format(endDate));
        List<Charge> entities = this.chargesJpaRepositories.findOneTimeChargesByDateAndUser(userId, startDate, endDate, categories);
        return entities.stream().map(ChargeMapper::map).collect(Collectors.toList());
    }

    private List<ChargeDto> findPeriodicCharges(Long userId, Date startDate, Date endDate, List<String> categories) {
        LOGGER.info("START SEARCHING FOR PERIODIC CHARGES");

        LOGGER.info("Searching PERIODIC charges for user between " + DATE_FORMAT.format(startDate) + " And " + DATE_FORMAT.format(endDate));

        List<Charge> entities = this.chargesJpaRepositories.findPeriodicChargesByDateAndUser(userId, categories);
        List<ChargeDto> dtos =  entities.stream()
                .filter(c -> ((PeriodicCharge) c).getEndDate() == null || ((PeriodicCharge) c).getEndDate().after(endDate)) // Filter on end date
                .map(c -> this.getChargeInstance(((PeriodicCharge) c), startDate, endDate))
                .filter(c -> c !=null)
                .collect(Collectors.toList());
        LOGGER.info("END SEARCHING FOR PERIODIC CHARGES");

        return dtos;
    }

    private ChargeDto getChargeInstance(PeriodicCharge periodicCharge, Date startDate, Date endDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(periodicCharge.getStartDate());
        ChargeDto chargeDto = null;
        if (startDate.compareTo(calendar.getTime()) * calendar.getTime().compareTo(endDate) >= 0){
            chargeDto = ChargeMapper.map(periodicCharge);
            chargeDto.setDebitDate(calendar.getTime());
        }
        while (chargeDto == null && calendar.getTime().compareTo(endDate) <=0) { // While the calenderDate is NOT between the startDate and the endDate
            incrementDate(calendar, periodicCharge.getPeriod());
            if (startDate.compareTo(calendar.getTime()) * calendar.getTime().compareTo(endDate) >= 0) {
                chargeDto = ChargeMapper.map(periodicCharge);
                chargeDto.setDebitDate(calendar.getTime());
            }
        }
        return chargeDto;
    }

    @Transactional
    public void deleteCharge(Long chargeId) {
        this.chargesJpaRepositories.deleteById(chargeId);
    }
}
