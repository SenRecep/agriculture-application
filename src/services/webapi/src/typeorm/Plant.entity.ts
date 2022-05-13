import { Column, Entity, JoinColumn, ManyToOne } from 'typeorm';
import { EntityBase } from './EntityBase.entity';
import { User } from './User.entity';

@Entity({ name: 'plants' })
export class Plant extends EntityBase {
  @Column({ name: 'name', nullable: false, type: 'longtext' })
  name: string;
  @Column({ name: 'fertilizer', nullable: false, type: 'longtext' })
  fertilizer: string;
  @Column({ name: 'irrigation', nullable: false, type: 'longtext' })
  irrigation: string;
  @Column({ name: 'planting', nullable: false, type: 'longtext' })
  planting: string;
  @Column({ name: 'harvest', nullable: false, type: 'longtext' })
  harvest: string;
  @Column({ name: 'content', nullable: false, type: 'longtext' })
  content: string;
  @ManyToOne(() => User)
  @JoinColumn()
  user: User;
  @Column({ name: 'userId', nullable: false })
  userId: number;
}
