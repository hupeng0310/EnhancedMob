package online.hupeng.enhancedmob.entitys.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EnhancedZombie extends Zombie {
    public EnhancedZombie(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * 是否对阳光敏感
     */
    @Override
    protected boolean isSunSensitive() {
        return super.isSunSensitive();
    }

    /**
     * 当前tick是否会被阳关灼伤
     */
    @Override
    protected boolean isSunBurnTick() {
        return false;
    }

    /**
     * 注册增强僵尸的属性
     */
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.MAX_HEALTH, 40.0D);
    }

}
