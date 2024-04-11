package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropCreationDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.service.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FarmSevice farmSevice;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository    the crop repository
   * @param farmSevice        the farm sevice
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmSevice farmSevice,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmSevice = farmSevice;
    this.fertilizerService = fertilizerService;
  }

  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Create crop crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  public CropDto createCrop(Long farmId, CropCreationDto cropCreationDto)
      throws FarmNotFoundException {
    farmSevice.findById(farmId);
    Crop crop = cropCreationDto.toEntity();
    crop.setFarmId(farmId);

    cropRepository.save(crop);

    return CropDto.fromEntity(crop);
  }

  public List<Crop> findCropsByFarmId(Long farmId) throws FarmNotFoundException {
    farmSevice.findById(farmId);
    return cropRepository.findByFarmId(farmId);
  }

  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  public Crop findCropById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findCropsByHarvestDateRange(LocalDate startDate, LocalDate endDate) {
    return cropRepository.findByHarvestDateBetween(startDate, endDate);
  }

  /**
   * Add crop fertilizer.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public void associateCropFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findCropById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    crop.getFertilizers().add(fertilizer);

    cropRepository.save(crop);
  }

  /**
   * Remove crop fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Crop removeAssociationCropFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findCropById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    crop.getFertilizers().remove(fertilizer);

    return cropRepository.save(crop);
  }

  /**
   * Find fertilizer by crop id list.
   *
   * @param cropId the crop id
   * @return the list
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Fertilizer> findFertilizerByCropId(Long cropId) throws CropNotFoundException {
    Crop crop = findCropById(cropId);

    return crop.getFertilizers();
  }
}
