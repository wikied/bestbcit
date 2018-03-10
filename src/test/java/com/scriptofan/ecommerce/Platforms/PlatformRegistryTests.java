package com.scriptofan.ecommerce.Platforms;

import com.scriptofan.ecommerce.Exception.AlreadyRegisteredException;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRuleset;
import com.scriptofan.ecommerce.Platforms.Interface.ItemBuilderRulesetFactory;
import com.scriptofan.ecommerce.Platforms.Interface.PlatformRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlatformRegistryTests {

    @Autowired
    private PlatformRegistry platformRegistry;

    @Test(expected = NullPointerException.class)
    public void shouldCatchNullRepositories() throws AlreadyRegisteredException {
        platformRegistry.registerPlatformRepository(null);
    }

    @Test
    public void should1ReturnSameNumberOfReposAdded() throws AlreadyRegisteredException {
        assert(platformRegistry.getItemBuilderRulesets().size() == 0);

        platformRegistry.registerPlatformRepository(
            new PlatformRepository() {
                public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() { return null; }
                public ItemBuilderRuleset getNewItemBuilderRuleset() { return null; }
            }
        );
        assert(platformRegistry.getItemBuilderRulesets().size() == 1);

        platformRegistry.registerPlatformRepository(
                new PlatformRepository() {
                    public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() { return null; }
                    public ItemBuilderRuleset getNewItemBuilderRuleset() { return null; }
                }
        );
        assert(platformRegistry.getItemBuilderRulesets().size() == 2);
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void shouldCatchDuplicateRepoInstances() throws AlreadyRegisteredException {
        /* Dummy repository for use in testing */
        class DummyRepository implements PlatformRepository {
            @Override
            public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() {
                return null;
            }
            @Override
            public ItemBuilderRuleset getNewItemBuilderRuleset() {
                return null;
            }
        }

        DummyRepository dummyRepository = new DummyRepository();

        platformRegistry.registerPlatformRepository(dummyRepository);
        platformRegistry.registerPlatformRepository(dummyRepository);
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void shouldCatchDuplicateRepoClasses() throws AlreadyRegisteredException {
        /* Dummy repository for use in testing */
        class DummyRepository implements PlatformRepository {
            @Override
            public ItemBuilderRulesetFactory getItemBuilderRulesetFactory() {
                return null;
            }
            @Override
            public ItemBuilderRuleset getNewItemBuilderRuleset() {
                return null;
            }
        }

        DummyRepository dummyRepository1 = new DummyRepository();
        DummyRepository dummyRepository2 = new DummyRepository();

        platformRegistry.registerPlatformRepository(dummyRepository1);
        platformRegistry.registerPlatformRepository(dummyRepository2);
    }

}
