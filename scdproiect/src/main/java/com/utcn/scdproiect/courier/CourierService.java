package com.utcn.scdproiect.courier;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Courier createCourier(Courier courier){
        if(courier.getName() == null || courier.getEmail() == null || courier.getPassword() == null) {
            throw new RuntimeException("Datele nu sunt complete!");
        }
       
        if (courier.getPassword().length() < 8 || 
            !courier.getPassword().matches(".*[A-Z].*") || 
            !courier.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new RuntimeException("Parola trebuie sa aiba minim 8 caractere, o litera mare si un caracter special!");
        }

        if (!courier.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new RuntimeException("Emailul nu este in format corect!");
        }
        //courier.findByEmail(courier.getEmail());
        if (courierRepository.findByemail(courier.getEmail())!=null) {
            throw new RuntimeException("Emailul este deja folosit!");
        }
        if (courierRepository.findByname(courier.getName())!=null) {
            throw new RuntimeException("Numele de utilizator este deja folosit!");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptedPassword = bCryptPasswordEncoder.encode(courier.getPassword());
        courier.setPassword(bCryptedPassword);

        return courierRepository.save(courier);
    }

    public Courier findByEmailOrThrow(String email) {
        Courier c = courierRepository.findByemail(email);
        if (c == null) throw new RuntimeException("Emailul nu exista!");
        return c;
    }

    public void verifyPasswordOrThrow(String email, String rawPassword) {
        Courier c = findByEmailOrThrow(email);
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
        if (!enc.matches(rawPassword, c.getPassword())) {
            throw new RuntimeException("Parola este incorecta!");
        }
    }

    public String loginCourier(String email, String password) {
        Courier courier = courierRepository.findByemail(email);
        if (courier == null) {
            throw new RuntimeException("Emailul nu exista!");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(password, courier.getPassword())) {
            throw new RuntimeException("Parola este incorecta!");
        }

        return "Autentificare reusita!";
    }




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
    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierRepository.getAllCouriersWithoutPendingPackages();
    }

    public List<Object[]> getManagersAndDeliveredCount(){
        return courierRepository.getManagersAndDeliveredCount();
    }


}
