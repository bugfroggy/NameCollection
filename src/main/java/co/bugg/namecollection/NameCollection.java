package co.bugg.namecollection;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Sloppy mod just to collect a ton of Minecraft names in one place
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class NameCollection {

    // Full path to where mod data is stored
    public static final String fullPath = "namecollection/";
    public static final NameEventHandler handler = new NameEventHandler();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Create the mod's directory
        CsvUtil.createDir(fullPath);

        CsvUtil.write("bugfroggy");
        // Register event handlers
        MinecraftForge.EVENT_BUS.register(handler);
    }
}
