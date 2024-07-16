package online.hupeng.enhancedmob;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import online.hupeng.enhancedmob.entitys.EnhancedEntityType;
import org.slf4j.Logger;


@Mod(EnhancedMob.MODID)
public class EnhancedMob {

    public static final String MODID = "enhanced_mob";

    private static final Logger LOGGER = LogUtils.getLogger();


    public EnhancedMob() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        //注册延迟实体注册器到mod总线上
        EnhancedEntityType.ENTITIES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Load mod EnhancedMob on the server side");
    }
}
