package com.utcn.scdproiect.pkg;

import com.utcn.scdproiect.courier.Courier;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    //CRUD: Read
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    //Create
    @Transactional
    public Package createPackage(Package pkg) {
        return packageRepository.save(pkg);
    }

    //TODO: UPDATE
    public Package updatePackageStatus(UpdatePackageRequest updatePackageRequest) {

        Package pack = packageRepository.findById(updatePackageRequest.getId())
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        pack.setStatus(updatePackageRequest.getStatus());

        return packageRepository.save(pack);
    }

    //TODO: DELETE
    public String deletePackage(Integer id) { //packageRepository.delete(pkg);

        try {
            packageRepository.deleteById(id);
        } catch (Exception e) {
            return e.toString();
        }
        return "pachetul a fost sters";

    }
    //TODO: chestiile din readme



    public List<Package> getPackagesForCourier(Integer id) {
        List<Package> packages = packageRepository.findByCourierId(id);
        if (packages.isEmpty()) {
            System.out.println("Curierul nu a livrat pachete");
        }
        return packages;
    }



}
