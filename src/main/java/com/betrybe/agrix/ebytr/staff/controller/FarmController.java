package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.controller.dto.CropCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmSevice;
import com.betrybe.agrix.ebytr.staff.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  private final FarmSevice farmSevice;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmSevice the farm sevice
   */
  public FarmController(FarmSevice farmSevice, CropService cropService) {
    this.farmSevice = farmSevice;
    this.cropService = cropService;
  }

  /**
   * Find all farms list.
   *
   * @return the list
   */
  @GetMapping
  @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER') or hasAuthority('USER')")
  public List<FarmDto> findAllFarms() {
    List<Farm> allFarms = farmSevice.findAll();
    return allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmSevice.findById(id)
    );
  }

  /**
   * Create farm dto.
   *
   * @param newFarm the new farm
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto newFarm) {
    return FarmDto.fromEntity(
        farmSevice.createFarm(newFarm.toEntity())
    );
  }

  /**
   * Create crop crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createCrop(@PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto) throws FarmNotFoundException {
    return cropService.createCrop(farmId, cropCreationDto);
  }

  /**
   * Gets crops bt farm id.
   *
   * @param farmId the farm id
   * @return the crops bt farm id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getCropsByFarmId(@PathVariable Long farmId) throws FarmNotFoundException {
    List<Crop> crops = cropService.findCropsByFarmId(farmId);
    return crops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }
}
