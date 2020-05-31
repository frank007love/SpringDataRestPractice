package org.tonylin.practice.spring.jpa.json.converter;

import org.apache.commons.dbcp.BasicDataSource;


public class AppConfigWithUnspecString extends BaseAppConfig {
	protected void postDataSourceSetup(BasicDataSource ds) {
		ds.setUrl(ds.getUrl()+"?stringtype=unspecified");
	}
}
