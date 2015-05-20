package co.valdeon.Tribes.components.abilities;

import co.valdeon.Tribes.components.Ability;
import co.valdeon.Tribes.components.AbilityType;
import co.valdeon.Tribes.util.TribeLoader;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbilityWaterBreathing extends Ability {

    public AbilityWaterBreathing(Player p, int i) {
        super(p, i, "waterbreathing");
    }

    @Override
    public void run() {
        if(!chunkOwned()) {
            this.p.removePotionEffect(PotionEffectType.WATER_BREATHING);
            this.cancel();
            return;
        }

        AbilityType b = null;
        for(AbilityType ba : TribeLoader.getTribe(this.p).getAbilities()) {
            if(ba.getText().equals(AbilityType.WATERBREATHING.getText())) {
                b = ba;
            }
        }

        if(b != null) {
            this.multi = b.getMultiplier();
        }

        for(PotionEffect pe : this.p.getActivePotionEffects()) {
            if(pe.getType().equals(PotionEffectType.WATER_BREATHING) && pe.getAmplifier() != this.multi) {
                this.p.removePotionEffect(PotionEffectType.WATER_BREATHING);
            }
        }

        this.p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 60 * 60 * 20, multi));
    }

}
