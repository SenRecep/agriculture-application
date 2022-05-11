import { Column, Entity, JoinColumn, ManyToOne } from 'typeorm';
import { EntityBase } from './EntityBase.entity';
import { User } from './User.entity';

@Entity({ name: 'plants' })
export class Plant extends EntityBase {
  @Column({ name: 'name', nullable: false, default: '' })
  name: string;
  @Column({ name: 'fertilizer', nullable: false, default: '' })
  fertilizer: string;
  @Column({ name: 'irrigation', nullable: false, default: '' })
  irrigation: string;
  @Column({ name: 'planting', nullable: false, default: '' })
  planting: string;
  @Column({ name: 'harvest', nullable: false, default: '' })
  harvest: string;
  @Column({ name: 'content', nullable: false, default: '' })
  content: string;
  @ManyToOne(() => User)
  @JoinColumn()
  user: User;
  @Column({ name: 'userId', nullable: false })
  userId: number;
}
