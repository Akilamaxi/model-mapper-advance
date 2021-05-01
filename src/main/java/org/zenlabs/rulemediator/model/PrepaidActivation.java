package org.zenlabs.rulemediator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PrepaidActivation {
    private String orderType;
//    private LocalDateTime requestedDate;
//    private String requestedUser;
    private Long contractId;
//    private List<String> productPromoVasKalturaMapping;
//    private String productIsPromoVas;
    private List<Double> productPromoVasPromoPackageRental;
    private List<String> subscriptionPackageCodes;

    private Integer requiredPeriodValue;
    private String requestedAction;
}
