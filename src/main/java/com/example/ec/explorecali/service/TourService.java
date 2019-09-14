package com.example.ec.explorecali.service;

import com.example.ec.explorecali.Domain.Difficulty;
import com.example.ec.explorecali.Domain.Region;
import com.example.ec.explorecali.Domain.Tour;
import com.example.ec.explorecali.Domain.TourPackage;
import com.example.ec.explorecali.repository.TourPackageRepository;
import com.example.ec.explorecali.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TourService {

    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, Integer price, String blurb,
                           String duration, String bullets, String keywords,
                           String tourPackageCode, Difficulty difficulty, Region region){

        TourPackage tourPackage = tourPackageRepository.findById(tourPackageCode).orElse(null);

        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets,
                tourPackage, difficulty, region));
    }

    public Iterable<Tour> lookup(){
        return tourRepository.findAll();
    }

    public long count(){
        return tourRepository.count();
    }

}
