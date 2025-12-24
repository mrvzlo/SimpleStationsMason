package com.ave.simplestationsmason.screen;

import com.ave.simplestationsmason.SimpleStationsMason;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
        public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister
                        .create(net.minecraft.core.registries.Registries.MENU, SimpleStationsMason.MODID);

        public static void register(IEventBus eventBus) {
                MENUS.register(eventBus);
        }

        public static final RegistryObject<MenuType<ExcavatorMenu>> EXCAVATOR_MENU = MENUS.register(
                        "excavator_menu", () -> IForgeMenuType.create(ExcavatorMenu::new));
        public static final RegistryObject<MenuType<MixerMenu>> MIXER_MENU = MENUS.register(
                        "mixer_menu", () -> IForgeMenuType.create(MixerMenu::new));
        public static final RegistryObject<MenuType<KilnMenu>> KILN_MENU = MENUS.register(
                        "kiln_menu", () -> IForgeMenuType.create(KilnMenu::new));
        public static final RegistryObject<MenuType<SifterMenu>> SIFTER_MENU = MENUS.register(
                        "sifter_menu", () -> IForgeMenuType.create(SifterMenu::new));

}
