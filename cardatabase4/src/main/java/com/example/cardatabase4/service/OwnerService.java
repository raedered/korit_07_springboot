package com.example.cardatabase4.service;

import com.example.cardatabase4.domian.Owner;
import com.example.cardatabase4.domian.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    public final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // 1. 모든 Owner 목록 조회
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    // 2. 새로운 Owner 저장
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    // 3. Owner 조회
    public Optional<Owner> getOwnerById(Long ownerId) {
        return ownerRepository.findById(ownerId);
    }

    // 4. Owner 삭제
    public boolean deleteOwner(Long ownerId) {
        if(ownerRepository.existsById(ownerId)) {
            ownerRepository.deleteById(ownerId);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Owner> updateOwner(Long ownerId, Owner ownerDetails) {
        return ownerRepository.findById(ownerId)
                .map(owner -> {
                    owner.setFirstName(ownerDetails.getFirstName());
                    owner.setLastName(ownerDetails.getLastName());
                    return owner;
                });
    }
}
