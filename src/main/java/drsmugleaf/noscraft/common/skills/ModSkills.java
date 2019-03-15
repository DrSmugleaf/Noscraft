package drsmugleaf.noscraft.common.skills;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import drsmugleaf.noscraft.Noscraft;
import drsmugleaf.noscraft.common.classes.ITransformation;
import drsmugleaf.noscraft.common.classes.SpecialistCards;
import drsmugleaf.noscraft.common.registry.ModRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by DrSmugleaf on 20/02/2019
 */
@Mod.EventBusSubscriber(modid = Noscraft.MOD_ID)
public class ModSkills {

    private static int nextID = 0;
    private static final @Nonnull String CSV_PATH = Noscraft.ASSETS + "/csv/skill/";
    public static final @Nonnull ResourceLocation TRANSFORMATION_SKILLS = new ResourceLocation(Noscraft.MOD_ID, "transformationtoskills");
    public static final @Nonnull ResourceLocation SKILLS_REGISTRY_NAME = new ResourceLocation(Noscraft.MOD_ID, "skills");
    public static final @Nonnull IForgeRegistry<ISkill> SKILLS = createRegistry();

    @Nonnull
    private static IForgeRegistry<ISkill> createRegistry() {
        return ModRegistries
                .makeRegistry(SKILLS_REGISTRY_NAME, ISkill.class)
                .add((IForgeRegistry.AddCallback<ISkill>) (owner, stage, id, obj, oldObj) -> {
                    @SuppressWarnings("unchecked")
                    Map<ITransformation, Set<ISkill>> map = owner.getSlaveMap(TRANSFORMATION_SKILLS, Map.class);
                    ITransformation transformation = obj.getTransformation();
                    if (transformation == null) {
                        return;
                    }
                    // TODO: 23/02/2019 Class skills

                    map.putIfAbsent(transformation, new HashSet<>());

                    if (oldObj != null) {
                        map.get(transformation).remove(oldObj);
                    }

                    map.get(transformation).add(obj);
                })
                .add((IForgeRegistry.ClearCallback<ISkill>) (owner, stage) -> {
                    owner.getSlaveMap(TRANSFORMATION_SKILLS, Map.class).clear();
                })
                .add((IForgeRegistry.CreateCallback<ISkill>) (owner, stage) -> {
                    Map<ITransformation, Set<ISkill>> map = new HashMap<>();
                    owner.setSlaveMap(TRANSFORMATION_SKILLS, map);
                }).create();
    }

    @Nonnull
    private static String getCsvPath(@Nonnull ITransformation transformation) {
        String cardName = transformation.getFileName();
        if (transformation.getClasses().size() == 1) {
            String className = transformation.getClasses().iterator().next().name().toLowerCase();
            return CSV_PATH + className + "/" + cardName + ".csv";
        } else {
            return CSV_PATH + "all/" + cardName + ".csv";
        }
    }

    @Nonnull
    public static Set<ISkill> parseCSV(@Nonnull ITransformation transformation) {
        Set<ISkill> skills = new HashSet<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(',').withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS).build();

        String filePath = getCsvPath(transformation);
        try (
                FileReader fileReader = new FileReader(filePath);
                CSVReaderHeaderAware reader = (CSVReaderHeaderAware) new CSVReaderHeaderAwareBuilder(fileReader)
                        .withCSVParser(parser)
                        .build()
        ) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                SkillMod skill = SkillMod.fromCSV(nextID, transformation, line);
                skills.add(skill);
                nextID++;
            }
        } catch (FileNotFoundException e) {
            Noscraft.LOG.error("File " + filePath + " not found"); // TODO: 20/02/2019 Remove when all skill csvs are added
        } catch (IOException e) {
            throw new IllegalStateException("Error reading file " + filePath, e);
        }

        return skills;
    }

    @SubscribeEvent
    public static void registerSkills(RegistryEvent.Register<ISkill> event) {
        for (ITransformation card : SpecialistCards.values()) {
            for (ISkill skill : parseCSV(card)) {
                event.getRegistry().register(skill);
                getSPSkills(card).add(skill);
            }
        }
    }

    @SubscribeEvent
    public static void registerSkillsTextures(TextureStitchEvent.Pre event) {
        for (ISkill skill : SKILLS) {
            event.getMap().registerSprite(skill.getTexture());
        }
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public static Set<ISkill> getSPSkills(@Nonnull ITransformation transformation) {
        Map<ITransformation, Set<ISkill>> map = SKILLS.getSlaveMap(TRANSFORMATION_SKILLS, Map.class);
        map.putIfAbsent(transformation, new HashSet<>());
        return map.get(transformation);
    }

}
