import com.butter.commands.MainCommand
import com.butter.config.Config
import com.butter.config.PersistentData
import com.butter.features.chat.ChatTime
import com.butter.features.chat.CopyChat
import com.butter.features.chat.FirstTime
import com.butter.features.gui.GuiFeatures
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.ModMetadata
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Keyboard
import java.io.File

@Mod(
    modid = "butterextras",
    name = "Butter Extras",
    version = "1.0",
    useMetadata = true,
    clientSideOnly = true
)
class ButterExtras {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        metadata = event.modMetadata
        val directory = File(event.modConfigurationDirectory, "butterextras")
        directory.mkdirs()
        configDirectory = directory
        persistentData = PersistentData.load()
        config = Config.apply { this.initialize() }
        print(event.modConfigurationDirectory)
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        ClientCommandHandler.instance.registerCommand(MainCommand())

        listOf(
            this,
            ChatTime,
            FirstTime,
            CopyChat,
            GuiFeatures,
        ).forEach(MinecraftForge.EVENT_BUS::register)

        keyBinds.values.forEach(ClientRegistry::registerKeyBinding)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || currentGui == null) return
        mc.displayGuiScreen(currentGui)
        currentGui = null
    }

    companion object {
        val mc: Minecraft = Minecraft.getMinecraft()
        var currentGui: GuiScreen? = null

        lateinit var configDirectory: File
        lateinit var config: Config
        lateinit var persistentData: PersistentData

        lateinit var metadata: ModMetadata

        val keyBinds = mapOf(
            "AutoBazaar" to KeyBinding("Auto Claim Bazaar Orders", Keyboard.KEY_NONE, "Butter Extras"),
        )
    }
}