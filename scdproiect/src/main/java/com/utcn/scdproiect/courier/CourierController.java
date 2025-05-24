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

    @PostMapping("/create")
    public Courier createCourier(@RequestBody Courier courier) {
        return courierService.createCourier(courier);
    }
    @GetMapping
    public List<Courier> getAllCouriers() {
        return courierService.getAllCouriers();
    }

    @GetMapping("/login")
    public String loginCourier(@RequestParam String email, @RequestParam String password) {
        return courierService.loginCourier(email, password);
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

    @GetMapping("/all-managers-delivered")
    public List<Object[]> getManagersAndDeliveredCount(){
        return  courierService.getManagersAndDeliveredCount();

    }

}
