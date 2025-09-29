package com.example.cardatabase4.service;

import com.example.cardatabase4.domian.Car;
import com.example.cardatabase4.domian.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // 1. 모든 자동차 목록을 조회한다고 가정하겠습니다.
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    // 2. 새로운 자동차 저장
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    // 3. 차량 한 대 조회
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    // 4. 차량 한 대 삭제
    public boolean deleteCar(Long id) {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 5. 차량 한 대 수정
    @Transactional
    public Optional<Car> updateCar(Long id, Car carDetails) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setBrand(carDetails.getBrand());
                    car.setModel(carDetails.getModel());
                    car.setColor(carDetails.getColor());
                    car.setModel(carDetails.getModel());
                    car.setPrice(carDetails.getPrice());
                    car.setModelYear(carDetails.getModelYear());
                    car.setRegistrationNumber(carDetails.getRegistrationNumber());
                    return car;
                    // carRepository.save(car);가 아닙니다.
                    // @Transactional 가 자동으로 업데이트가 됩니다.
                });
    }
}
