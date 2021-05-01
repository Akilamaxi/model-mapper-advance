package org.zenlabs.rulemediator.model;

import lombok.Data;

import java.util.List;

@Data
public class Subscription {
    private List<DtvPackage> dtvPackageList;
}
