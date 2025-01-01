package com.utcn.scdproiect.pkg;

import lombok.Data;

@Data
public class UpdatePackageRequest {
    private Integer id; //id pachet
    private PackageStatus status;

}