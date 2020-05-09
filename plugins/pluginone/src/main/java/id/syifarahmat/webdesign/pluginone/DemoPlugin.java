package id.syifarahmat.webdesign.pluginone;

import lombok.extern.slf4j.Slf4j;
import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SharedDataSourceSpringBootstrap;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

@Slf4j
public class DemoPlugin extends SpringBootPlugin {
    public DemoPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }
    @Override
    protected SpringBootstrap createSpringBootstrap() {
        return new SharedDataSourceSpringBootstrap(this, PluginoneApplication.class)
                .addPresetProperty(DataSourceConfig.dataSourceBeanName, getMainApplicationContext().getBean(DataSourceConfig.dataSourceBeanName));
    }
}