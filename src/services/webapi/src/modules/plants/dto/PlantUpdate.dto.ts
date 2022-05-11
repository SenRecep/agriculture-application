import { IsString, MinLength } from 'class-validator';

export class PlantUpdateDto {
  @IsString()
  @MinLength(2)
  name?: string;
  @IsString()
  @MinLength(2)
  fertilizer?: string;
  @IsString()
  @MinLength(2)
  irrigation?: string;
  @IsString()
  @MinLength(2)
  planting?: string;
  @IsString()
  @MinLength(2)
  harvest?: string;
  @IsString()
  @MinLength(2)
  content?: string;
}
