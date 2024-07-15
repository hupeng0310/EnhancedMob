package online.hupeng.enhancedmob.entitys;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import online.hupeng.enhancedmob.EnhancedMob;
import online.hupeng.enhancedmob.entitys.entity.EnhancedZombie;

public class EnhancedEntityType {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EnhancedMob.MODID);

    public static final RegistryObject<EntityType<EnhancedZombie>> ENHANCE_ZOMBIE =ENTITIES.register("enhanced_zombie",
            () -> EntityType.Builder.of(EnhancedZombie::new, MobCategory.MONSTER).sized(0.6F, 1.95F).build(EnhancedMob.MODID + ":enhanced_zombie"));

}
