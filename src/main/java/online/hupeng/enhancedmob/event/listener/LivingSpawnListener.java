package online.hupeng.enhancedmob.event.listener;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.hupeng.enhancedmob.EnhancedMob;
import online.hupeng.enhancedmob.entitys.EnhancedEntityType;
import online.hupeng.enhancedmob.entitys.entity.EnhancedZombie;

@Mod.EventBusSubscriber(modid = EnhancedMob.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
public class LivingSpawnListener {

    @SubscribeEvent
    public static void livingSpawn(LivingSpawnEvent event) {
        Mob entity = event.getEntity();
        if (entity.getClass() == Zombie.class) {
            Zombie zombie = (Zombie) entity;
            if (!zombie.getTags().contains("not_enhanced")) {
                double enhanceRandom = event.getLevel().getRandom().nextDouble();
                boolean canSeeSky = zombie.getLevel().canSeeSky(zombie.blockPosition());
                if (!canSeeSky) {
                    enhanceRandom = enhanceRandom * 2;
                }
                if (enhanceRandom < 0.1D) {
                    EnhancedZombie enhancedZombie = zombie.convertTo(EnhancedEntityType.ENHANCE_ZOMBIE.get(), true);
                    if (enhancedZombie == null) {
                        throw new RuntimeException("Failed to convert zombie to enhanced zombie");
                    }
                    enhancedZombie.generateEquipments();
                    return;
                }
            }
            zombie.addTag("not_enhanced");
        }
    }
}
