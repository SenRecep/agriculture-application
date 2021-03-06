import {
  Body,
  ClassSerializerInterceptor,
  Controller,
  Get,
  Inject,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  Req,
  UseFilters,
  UseInterceptors,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { Request as ExpressRequest } from 'express';
import { HttpExceptionFilter } from 'src/filters/HttpException.filter';
import { Public } from 'src/modules/auth/guards/Public.guard';
import { CreateUserDto } from '../../dto/CreateUser.dto';
import { UserListDto } from '../../dto/UserList.dto';
import { UsersService } from '../../services/users/users.service';

@Controller('users')
export class UsersController {
  constructor(
    @Inject('UsersService') private readonly userService: UsersService,
  ) {}

  @UseInterceptors(ClassSerializerInterceptor)
  @Get('')
  getUsers() {
    return this.userService.getUsers();
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseFilters(HttpExceptionFilter)
  @Get('id/:id')
  async getUserById(@Param('id', ParseIntPipe) id: number) {
    const user = await this.userService.findById(id);
    if (!user) throw new NotFoundException();
    return user;
  }

  @Get('isAdmin')
  isAdmin(@Req() req: ExpressRequest) {
    return new UserListDto(req.user).isAdmin;
  }

  @UseInterceptors(ClassSerializerInterceptor)
  @UseFilters(HttpExceptionFilter)
  @Get()
  async getUserByName(@Param('email') email: string) {
    const user = await this.userService.findByUserEmail(email);
    if (!user) throw new NotFoundException();
    return user;
  }

  @Public()
  @Post('create')
  @UsePipes(ValidationPipe)
  createUser(@Body() createUserDto: CreateUserDto) {
    return this.userService.createUser(createUserDto);
  }
}
