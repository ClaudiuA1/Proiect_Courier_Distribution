package com.utcn.scdproiect.pkg;

import com.utcn.scdproiect.courier.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
@CrossOrigin
public class PackageController {
    @Autowired
    private PackageService packageService;

    @PostMapping
    public Package createPackage(@RequestBody Package pkg) {
        return packageService.createPackage(pkg);
    }

    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PutMapping
    public Package updatePackageStatus(@RequestBody UpdatePackageRequest upr) {
        try {
            return packageService.updatePackageStatus(upr);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    @DeleteMapping
    public String deletePackage(Integer id) {
        return packageService.deletePackage(id);

    }
    @GetMapping("/CourierPackage")
    public List<Package> getPackagesForCourier(Integer id) {
        return packageService.getPackagesForCourier(id);

    }
    @GetMapping("/NoCourier")
    public List<Package> getPackagesNoCourier(){
        return packageService.getPackagesNoCourier();
    }
    @PutMapping("/AssignCourier/{idPkg}/{idCor}")
    public Package assignPackageCourier(@PathVariable Integer idPkg, @PathVariable Integer idCor) {
        System.out.println("Id pachet: " + idPkg);
        System.out.println("Courier: " + idCor);

        return packageService.assignPackageCourier(idPkg, idCor);
    }





}
