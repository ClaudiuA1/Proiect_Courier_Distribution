package com.utcn.scdproiect.pkg;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    //CRUD: Read
    public List<Package> getAllPackages(){
        return packageRepository.findAll();
    }

    //Create
    @Transactional
    public Package createPackage(Package pkg){
        return packageRepository.save(pkg);
    }

    //TODO: UPDATE
    //TODO: DELETE
    //TODO: chestiile din readme
    //TODO: getPackagesForCourier

//    public List<Package> getPackagesForCourier(Courier courier){
//        //return packageRepository
//    }

}
