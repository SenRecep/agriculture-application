import {
  Body,
  Controller,
  Delete,
  Get,
  Inject,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  Put,
  Query,
  Req,
  UseFilters,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { Request } from 'express';
import { HttpExceptionFilter } from 'src/filters/HttpException.filter';
import { UserListDto } from 'src/modules/users/dto/UserList.dto';
import { PlantCreateDto } from '../../dto/PlantCreate.dto';
import { PlantUpdateDto } from '../../dto/PlantUpdate.dto';
import { PlantsService } from '../../services/plants/plants.service';

@Controller('plants')
export class PlantsController {
  constructor(
    @Inject(PlantsService) private readonly plantsService: PlantsService,
  ) {}

  @Get()
  getAll() {
    return this.plantsService.getAllWhitoutDeleted();
  }
  @Get('pager')
  async getAllPager(@Query() { take, skip }) {
    return await this.plantsService.getAllPager(take, skip);
  }
  @Get('/checkowner/:plantId')
  async checkOwner(
    @Param('plantId', ParseIntPipe) plantId: number,
    @Req() req: Request,
  ) {
    const found = await this.plantsService.getByIdAsync(plantId);
    const user = new UserListDto(req.user);
    return found?.userId === user.id;
  }

  @Get(':id')
  @UseFilters(HttpExceptionFilter)
  async getById(@Param('id', ParseIntPipe) id: number) {
    const found = await this.plantsService.getByIdAsync(id);
    if (!found) throw new NotFoundException('Post not found');
    return found;
  }

  @Delete(':id')
  delete(@Param('id', ParseIntPipe) id: number) {
    return this.plantsService.deleteAsync(id);
  }
  @Put(':id')
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() plantUpdateDto: PlantUpdateDto,
  ) {
    return this.plantsService.updateAsync(id, plantUpdateDto);
  }

  @Post()
  @UsePipes(ValidationPipe)
  createUser(@Body() plantCreateDto: PlantCreateDto, @Req() req: Request) {
    plantCreateDto.userId = req.user['id'];
    return this.plantsService.createAsync(plantCreateDto);
  }
}
