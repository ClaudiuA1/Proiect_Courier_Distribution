package com.utcn.scdproiect.pkg;

import com.utcn.scdproiect.courier.Courier;
import com.utcn.scdproiect.courier.CourierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private CourierRepository courierRepository;

    //CRUD: Read
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    //Create
    @Transactional
    public Package createPackage(Package pkg) {
        return packageRepository.save(pkg);
    }

    public Package updatePackageStatus(UpdatePackageRequest updatePackageRequest) {

        Package pack = packageRepository.findById(updatePackageRequest.getId())
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        pack.setStatus(updatePackageRequest.getStatus());

        return packageRepository.save(pack);
    }

    public String deletePackage(Integer id) { //packageRepository.delete(pkg);

        try {
            packageRepository.deleteById(id);
        } catch (Exception e) {
            return e.toString();
        }
        return "pachetul a fost sters";

    }

    public List<Package> getPackagesForCourier(Integer id) {
        List<Package> packages = packageRepository.findByCourierId(id);
        if (packages.isEmpty()) {
            System.out.println("Curierul nu a livrat pachete");
        }
        return packages;
    }

    public List<Package> getPackagesNoCourier() {
        List<Package> packages = packageRepository.findAll(); // Obține toate pachetele
        List<Package> packagesNoCourier = new ArrayList<>(); // Listă pentru pachetele fără curier

        // Iterează prin fiecare pachet din lista originală
        for (Package p : packages) {
            if (p.getCourier() == null) {  // Verifică dacă pachetul nu are un curier
                packagesNoCourier.add(p);  // Adaugă pachetul la lista de pachete fără curier
            }
        }

        return packagesNoCourier;  // Returnează lista cu pachetele fără curier
    }
    public Package assignPackageCourier(Integer idPkg, Integer courierId) {
        // Verificăm dacă pachetul există
        Package pack = packageRepository.findById(idPkg)
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        // Verificăm dacă curierul este valid
        if (courierId == null) {
            throw new RuntimeException("Curierul nu a fost găsit!");
        }

        // Căutăm curierul în baza de date
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(() -> new RuntimeException("Curierul nu a fost găsit!"));

        // Atribuim curierul pachetului
        pack.setCourier(courier);

        // Salvăm modificările în baza de date
        return packageRepository.save(pack);
    }






}
