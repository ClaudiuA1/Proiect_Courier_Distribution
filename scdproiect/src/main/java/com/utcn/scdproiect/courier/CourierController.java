package com.utcn.scdproiect.courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courier")
@CrossOrigin
public class CourierController {
    @Autowired
    private CourierService courierService;

    @PostMapping
    public Courier createCourier(@RequestBody Courier courier) {
        return courierService.createCourier(courier);
    }
    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    /*@PutMapping
    public Package updateCourierManager(@RequestBody Integer upr) {
        try {
            return packageService.updatePackageStatus(upr);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }*/

    @DeleteMapping
    public String deletePackage(Integer id) {
        return courierService.deleteCourier(id);

    }
    @GetMapping("/without-pending-packages")
    public List<Courier> getCouriersWithoutPendingPackages() {
        return courierService.getAllCouriersWithoutPendingPackages();
    }

}
