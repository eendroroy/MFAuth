package com.indrajit

import griffon.core.artifact.ArtifactManager
import griffon.core.test.GriffonUnitRule
import griffon.core.test.TestFor
import org.junit.Rule
import org.junit.Test

import javax.inject.Inject

import static org.awaitility.Awaitility.await
import static java.util.concurrent.TimeUnit.SECONDS

@TestFor(MFAuthController)
class MFAuthControllerTest {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'trace')
    }

    @Inject
    private ArtifactManager artifactManager

    private MFAuthController controller

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    @Test
    void executeClickAction() {
        // given:
        controller.model = artifactManager.newInstance(MFAuthModel)

        // when:
        controller.invokeAction('updateToken')
        await().atMost(2, SECONDS)

        // then:
        assert 1 == controller.model.authToken
    }
}
