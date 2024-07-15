package online.hupeng.enhancedmob.event.listener;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import online.hupeng.enhancedmob.EnhancedMob;
import online.hupeng.enhancedmob.entitys.EnhancedEntityType;
import online.hupeng.enhancedmob.entitys.entity.EnhancedZombie;

@Mod.EventBusSubscriber(modid = EnhancedMob.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeRegistryListener {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EnhancedEntityType.ENHANCE_ZOMBIE.get(), EnhancedZombie.createAttributes().build());
    }
}
