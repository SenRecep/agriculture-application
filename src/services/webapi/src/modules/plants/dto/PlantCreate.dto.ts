import { IsNotEmpty, IsString, MinLength } from 'class-validator';

export class PlantCreateDto {
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  name: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  fertilizer: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  irrigation: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  planting: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  harvest: string;
  @IsNotEmpty()
  @IsString()
  @MinLength(2)
  content: string;

  userId?: number = null;
}
