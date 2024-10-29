package com.utcn.scdproiect.courier;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    //CRUD: Read
    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    //Create
    @Transactional
    public Courier createCourier(Courier courier) {
        return courierRepository.save(courier);
    }

    //TODO: UPDATE
   /* public Package updatePackageStatus(UpdatePackageRequest updatePackageRequest) {

        Package pack = CourierRepository.findById(updatePackageRequest.getId())
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        pack.setStatus(updatePackageRequest.getStatus());

        return CourierRepository.save(pack);
    }*/

    //TODO: DELETE
    public String deleteCourier(Integer id) { //packageRepository.delete(pkg);

        try {
            courierRepository.deleteById(id);
        } catch (Exception e) {
            return e.toString();
        }
        return "Curierul a fost sters";

    }
    //TODO: chestiile din readme
    //TODO: getPackagesForCourier


//    public List<Package> getPackagesForCourier(Courier courier){
//        //return packageRepository
//    }

}
