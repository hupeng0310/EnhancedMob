package online.hupeng.enhancedmob.event.listener;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.hupeng.enhancedmob.EnhancedMob;
import online.hupeng.enhancedmob.entitys.EnhancedEntityType;
import online.hupeng.enhancedmob.entitys.entity.EnhancedZombie;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = EnhancedMob.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ZombieSpawnListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onZombieSpawn(LivingSpawnEvent.SpecialSpawn event) {
        Mob mob = event.getEntity();
        if (mob.getClass() == Zombie.class) {
            Zombie zombie = (Zombie) mob;
            double enhanceRandom = event.getLevel().getRandom().nextDouble();
            boolean canSeeSky = zombie.getLevel().canSeeSky(zombie.blockPosition());
            if (!canSeeSky) {
                enhanceRandom = enhanceRandom / 2;
            }
            if (enhanceRandom > 0.9D) {
                EnhancedZombie enhancedZombie = zombie.convertTo(EnhancedEntityType.ENHANCE_ZOMBIE.get(), true);
                if (enhancedZombie == null) {
                    throw new RuntimeException("Failed to convert zombie to enhanced zombie");
                }
                enhancedZombie.generateEquipments();
            }
        }
    }
}
