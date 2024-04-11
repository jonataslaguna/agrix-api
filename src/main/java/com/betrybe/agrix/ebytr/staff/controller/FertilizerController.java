package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import com.betrybe.agrix.ebytr.staff.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create farm fertilizer dto.
   *
   * @param newFertilizer the new fertilizer
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFarm(@RequestBody FertilizerCreationDto newFertilizer) {
    return FertilizerDto.fromEntity(
        fertilizerService.create(newFertilizer.toEntity())
    );
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<FertilizerDto> findAll() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    return fertilizers
        .stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

  /**
   * Find by id fertilizer dto.
   *
   * @param id the id
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public FertilizerDto findById(@PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.fromEntity(
        fertilizerService.findById(id)
    );
  }
}
