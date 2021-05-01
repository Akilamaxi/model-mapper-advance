package org.zenlabs.rulemediator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QualificationRequest {
    private String orderType;
//    private LocalDateTime requestedDate;
    private String requestedUser;
    private String contractId;
    private List<PromoVAS> promoVASList;
    private List<Subscription> subscriptionList;
    private List<EAVItem> eavItemList;
}
