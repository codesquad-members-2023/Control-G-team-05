package com.codesquad.controlG;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class DatabaseInitializerExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) {
        DatabaseCleanup databaseCleanup = (DatabaseCleanup) SpringExtension.getApplicationContext(context)
                .getBean("databaseCleanup");
        databaseCleanup.execute();
    }
}
