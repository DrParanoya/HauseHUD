package drparanoya.hausehud.addon;

import drparanoya.hausehud.addon.hud.VisionBlocker;
import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.hud.Hud;
import meteordevelopment.meteorclient.systems.hud.HudGroup;
import org.slf4j.Logger;

public class HauseHUD extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final HudGroup HUD_GROUP = new HudGroup("HauseHUD");

    @Override
    public void onInitialize() {
        LOG.info("Blocking your vision...");
        Hud.get().register(VisionBlocker.INFO);
    }

    private void initializeHud(Hud hud) {
        hud.register(VisionBlocker.INFO);
    }

    @Override
    public String getPackage() {
        return "drparanoya.hausehud.addon";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("DrParanoya", "HauseHUD");
    }
}
