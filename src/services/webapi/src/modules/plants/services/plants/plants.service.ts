import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Plant } from 'src/typeorm';
import { Repository } from 'typeorm';
import { PlantCreateDto } from '../../dto/PlantCreate.dto';
import { PlantUpdateDto } from '../../dto/PlantUpdate.dto';

@Injectable()
export class PlantsService {
  constructor(
    @InjectRepository(Plant)
    private readonly plantsRepository: Repository<Plant>,
  ) {}
  getByIdAsync(id: number): Promise<Plant> {
    return this.plantsRepository.findOne({
      where: { id },
    });
  }
  getAllAsync(): Promise<Plant[]> {
    return this.plantsRepository.find();
  }
  getAllWhitoutDeleted(): Promise<Plant[]> {
    return this.plantsRepository.find({
      where: { isDeleted: false },
    });
  }
  async getAllPager(take = 5, skip = 0): Promise<Plant[]> {
    const [data] = await this.plantsRepository.findAndCount({
      take,
      skip,
      where: { isDeleted: false },
      order: { updatedTime: 'DESC' },
    });
    return data;
  }

  async deleteAsync(id: number): Promise<Plant> {
    this.plantsRepository.update({ id }, { isDeleted: true });
    return await this.getByIdAsync(id);
  }
  async updateAsync(
    id: number,
    plantUpdateDto: PlantUpdateDto,
  ): Promise<Plant> {
    const found = await this.getByIdAsync(id);
    if (!found) throw new NotFoundException('Plant not found');
    for (const key in plantUpdateDto) {
      if (
        Object.prototype.hasOwnProperty.call(found, key) &&
        plantUpdateDto[key]
      ) {
        found[key] = plantUpdateDto[key];
      }
    }
    await this.plantsRepository.update({ id }, { ...found });
    return found;
  }

  async createAsync(plantCreateDto: PlantCreateDto): Promise<Plant> {
    const Plant = this.plantsRepository.create(plantCreateDto);
    return await this.plantsRepository.save(Plant);
  }
}
