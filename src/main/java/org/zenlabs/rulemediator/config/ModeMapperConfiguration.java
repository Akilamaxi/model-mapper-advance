package org.zenlabs.rulemediator.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zenlabs.rulemediator.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
public class ModeMapperConfiguration {

    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(QualificationRequest.class, PrepaidActivation.class)
                .addMappings(prepaidActivationMap);
        return modelMapper;
    }


    PropertyMap<QualificationRequest, PrepaidActivation> prepaidActivationMap = new PropertyMap<QualificationRequest, PrepaidActivation>() {
        protected void configure() {
            using(promoVasPackageRentalConverter).map(source.getPromoVASList()).setProductPromoVasPromoPackageRental(null);
            using(subscriptionPackageCodeConverter).map(source.getSubscriptionList()).setSubscriptionPackageCodes(null);
            using(requiredPeriodValueConverter).map(source.getEavItemList()).setRequiredPeriodValue(null);
            using(requestedActionConverter).map(source.getEavItemList()).setRequestedAction(null);
        }
    };
    Converter<List<PromoVAS>, List<Double>> promoVasPackageRentalConverter = mappingContext -> mappingContext
            .getSource()
            .parallelStream().filter(promoVAS -> Objects.nonNull(promoVAS.getPromoPackageRental()))
            .map(PromoVAS::getPromoPackageRental)
            .collect(Collectors.toList());

    Converter<List<Subscription>, List<String>> subscriptionPackageCodeConverter = mappingContext -> {
        final List<Subscription> source = mappingContext.getSource();
        if(!source.isEmpty()){
            return source.get(0)
                    .getDtvPackageList()
                    .stream()
                    .map(DtvPackage::getPackageCode)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    };
    Converter<List<EAVItem>, Integer> requiredPeriodValueConverter = mappingContext ->
            mappingContext.getSource()
            .parallelStream().filter(eavItem -> eavItem.getName().equals("requiredPeriodValue"))
                    .mapToInt(eavItem -> (Integer) eavItem.getValue()).findAny().orElse(0);

    Converter<List<EAVItem>, String> requestedActionConverter = mappingContext ->
            mappingContext.getSource()
            .parallelStream().filter(eavItem -> eavItem.getName().equals("requestedAction"))
                    .map(eavItem -> (String) eavItem.getValue()).findAny().orElse(null);

}
