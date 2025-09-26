package com.example.cardatabase;

import com.example.cardatabase.domain.Car;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.Owner;
import com.example.cardatabase.domain.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("차량저장 메서드 테스트")
    void saveCar() {
        // given - 제반 준비 사항
        // Car Entity를 확인해봤을 때 field로 Owner를 요구하기 때문에
        // 얘부터 먼저 만들고 -> ownerRepository에 저장
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        // 그리고 Car 객체를 만들겁니다.
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);

        // when - 테스트 실행
        // 저장이 됐는가를 확인하기위한 부분
        carRepository.save(car);
        // then - 그 결과가 어떠할지
        assertThat(carRepository.findById(car.getId())).isPresent();        // 이건 그냥 결과값이 하나일테니까.

        assertThat(carRepository.findById(car.getId()).get().getBrand()).isEqualTo("Ford");
    }

    @Test
    void deleteCar() {
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        Car car = new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner);
        carRepository.save(car);

        carRepository.deleteAll();

        assertThat(carRepository.count()).isEqualTo(0);
    }

    @Test
    void findByBrandShouldReturnCar() {
        Owner owner = new Owner("Gemini", "GPT");
        ownerRepository.save(owner);
        carRepository.save(new Car("Ford", "Mustang", "Red", "ABCDEF", 2021, 567890, owner));
        carRepository.save(new Car("Toyota", "Treno", "Black-White", "123456", 2021, 567890, owner));
        carRepository.save(new Car("Toyota", "GR86", "Blue", "987654", 2021, 567890, owner));

        List<Car> toyotas = carRepository.findByBrand("Toyota");
        // 그 객체의 getBrand()의 결과값이 우리가 findByBrand()의 arugment로 쓴 값과 동일한지를 체크할 수 있겠네요.
//        assertThat(toyotas.get(0).getBrand()).isEqualTo("Toyota");
        // 혹은, 현재 저희가 Toyota 차량을 두대 만들었으니가 size()의 결과값이 2겠죠.
        assertThat(toyotas.size()).isEqualTo(2);
    }
}
