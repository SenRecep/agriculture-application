import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Plant } from 'src/typeorm';
import { PlantsController } from './controllers/plants/plants.controller';
import { PlantsService } from './services/plants/plants.service';

@Module({
  imports: [TypeOrmModule.forFeature([Plant])],
  controllers: [PlantsController],
  providers: [PlantsService],
})
export class PlantsModule {}
