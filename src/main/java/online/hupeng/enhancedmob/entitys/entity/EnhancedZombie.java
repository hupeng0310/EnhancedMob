package online.hupeng.enhancedmob.entitys.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import online.hupeng.enhancedmob.EnhancedMob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnhancedZombie extends Zombie {
    public EnhancedZombie(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
        if (this.level.getDifficulty() == Difficulty.EASY) {
            this.xpReward = 10;
        } else if (this.level.getDifficulty() == Difficulty.NORMAL) {
            this.xpReward = 20;
        } else if (this.level.getDifficulty() == Difficulty.HARD) {
            this.xpReward = 25;
        }
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

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor,
                                        @NotNull DifficultyInstance difficultyInstance,
                                        @NotNull MobSpawnType mobSpawnType,
                                        @Nullable SpawnGroupData spawnGroupData,
                                        @Nullable CompoundTag compoundTag) {
        this.generateEquipments();
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    protected boolean convertsInWater() {
        return false;
    }

    public void generateEquipments() {
        ItemStack mainHandItem = ItemStack.EMPTY;
        ItemStack helmet = ItemStack.EMPTY;
        if (this.getLevel().getDifficulty() == Difficulty.EASY) {
            mainHandItem = new ItemStack(Items.IRON_SWORD);
        } else if (this.getLevel().getDifficulty() == Difficulty.NORMAL) {
            mainHandItem = new ItemStack(Items.IRON_AXE);
            if (this.getRandom().nextDouble() < 0.5D) {
                helmet = new ItemStack(Items.IRON_HELMET);
            }
        } else if (this.getLevel().getDifficulty() == Difficulty.HARD) {
            mainHandItem = new ItemStack(Items.DIAMOND_SWORD);
            helmet = new ItemStack(Items.IRON_HELMET);
            this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
            this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
            if (this.getRandom().nextDouble() < 0.2D) {
                mainHandItem.enchant(Enchantments.SHARPNESS, this.getRandom().nextInt(3) + 1);
            }
            if (this.getRandom().nextDouble() < 0.5D) {
                helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, this.getRandom().nextInt(1) + 1);
            }
        }
        this.setItemInHand(InteractionHand.MAIN_HAND, mainHandItem);
        this.setItemSlot(EquipmentSlot.HEAD, helmet);
    }

    @Override
    public @NotNull ResourceLocation getDefaultLootTable() {
        return new ResourceLocation(EnhancedMob.MODID, "enhanced_zombie");
    }

    /**
     * 注册增强僵尸的属性
     */
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Zombie.createAttributes()
                .add(Attributes.FOLLOW_RANGE, 64)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.MAX_HEALTH, 50.0D);
    }

}
