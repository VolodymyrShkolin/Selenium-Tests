package core;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class MonitorRule implements TestWatcher {

    /**
     * Invoked when a test disabled
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.printf("%s disabled with %s%n", context.getDisplayName(), reason);
        saveScreenshot(BaseTest.screenshot);
        TestWatcher.super.testDisabled(context, reason);
    }

    /**
     * Invoked when a test succeeds
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.printf("%s succeeded%n", context.getDisplayName());
        saveScreenshot(BaseTest.screenshot);
        TestWatcher.super.testSuccessful(context);
    }

    /**
     * Invoked when a test aborted
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.printf("%s aborted with %s%n", context.getDisplayName(), cause);
        saveScreenshot(BaseTest.screenshot);
        TestWatcher.super.testAborted(context, cause);
    }

    /**
     * Invoked when a test failed
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.printf("%s failed with %s%n", context.getDisplayName(), cause);
        saveScreenshot(BaseTest.screenshot);
        TestWatcher.super.testFailed(context, cause);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
