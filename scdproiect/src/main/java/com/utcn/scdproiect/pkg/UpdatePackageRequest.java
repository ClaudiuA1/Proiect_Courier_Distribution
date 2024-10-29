package com.utcn.scdproiect.pkg;

import lombok.Data;

@Data
public class UpdatePackageRequest {
    private Integer id;
    private PackageStatus status;

}