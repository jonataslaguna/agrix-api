package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.repository.FarmRepository;
import com.betrybe.agrix.ebytr.staff.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm sevice.
 */
@Service
public class FarmSevice {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmSevice(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id)
        .orElseThrow(FarmNotFoundException::new);
  }

  public Farm createFarm(Farm farm) {
    return farmRepository.save(farm);
  }

}

