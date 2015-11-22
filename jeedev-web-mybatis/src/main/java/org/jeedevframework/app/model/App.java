package org.jeedevframework.app.model;

import org.jeedevframework.core.common.domain.IDEntity;

public class App extends IDEntity {

    private String appCode;

    private String appName;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}