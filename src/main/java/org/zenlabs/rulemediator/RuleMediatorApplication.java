package org.zenlabs.rulemediator;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zenlabs.rulemediator.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Slf4j
@SpringBootApplication
public class RuleMediatorApplication implements CommandLineRunner {

    @Autowired
    ModelMapper modelMapper;

    public static void main(String[] args) {
        SpringApplication.run(RuleMediatorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        QualificationRequest request = new QualificationRequest();
        request.setContractId("2234343");
        request.setOrderType("ADD");

        PromoVAS promoVAS = new PromoVAS();
        promoVAS.setPromoPackageRental(12.0);

        PromoVAS promoVAS1 = new PromoVAS();

        request.setPromoVASList(Arrays.asList(promoVAS,promoVAS1));

        DtvPackage dtvPackage = new DtvPackage();
        dtvPackage.setPackageCode("TRAVELXP");
        DtvPackage dtvPackage1 = new DtvPackage();
        dtvPackage1.setPackageCode("SACHET");

        Subscription subscription = new Subscription();
        subscription.setDtvPackageList(Arrays.asList(dtvPackage,dtvPackage1));
        request.setSubscriptionList(Arrays.asList(subscription));

        EAVItem eavItem = new EAVItem();
        eavItem.setName("requiredPeriodValue");
        eavItem.setValue(122);

        EAVItem eavItem1 = new EAVItem();
        eavItem1.setName("requestedAction");
        eavItem1.setValue("C");

        request.setEavItemList(Arrays.asList(eavItem,eavItem1));

        PrepaidActivation prepaidActivation = new PrepaidActivation();
        modelMapper.map(request,prepaidActivation);
        log.info("PrepaidActivation result :: {}",prepaidActivation);

    }
}
