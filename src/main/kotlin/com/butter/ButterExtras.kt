import com.butter.commands.MainCommand
import com.butter.config.Config
import com.butter.config.PersistentData
import com.butter.features.chat.ChatTime
import com.butter.features.chat.CopyChat
import com.butter.features.chat.FirstTime
import com.butter.features.gui.GuiFeatures
import gg.essential.universal.UChat
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
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
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
        val directory = File(event.modConfigurationDirectory, event.modMetadata.modId)
        directory.mkdirs()
        configDirectory = directory
        persistentData = PersistentData.load()
        config = Config
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

        keyBinds.forEach(ClientRegistry::registerKeyBinding)
    }

    @SubscribeEvent
    fun onTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || currentGui == null) return
        mc.displayGuiScreen(currentGui)
        currentGui = null
    }

    @SubscribeEvent
    fun onKey(e: KeyInputEvent){
        if(keyBinds[0].isPressed && config.AutoBazaarClaimOrder) {
            UChat.say("/bz")
            autoBz = true
        }
    }
    companion object {
        val mc: Minecraft = Minecraft.getMinecraft()
        var currentGui: GuiScreen? = null

        lateinit var configDirectory: File
        lateinit var config: Config
        lateinit var persistentData: PersistentData

        lateinit var metadata: ModMetadata

        val keyBinds = arrayOf(
            KeyBinding("Auto Bazaar Claim Order", Keyboard.KEY_BACKSLASH, "Butter Extras")
        )

        var autoBz = false
    }
}