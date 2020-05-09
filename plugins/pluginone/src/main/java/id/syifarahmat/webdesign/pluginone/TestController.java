package id.syifarahmat.webdesign.pluginone;

import com.zaxxer.hikari.HikariDataSource;
import id.syifarahmat.webdesign.pluginone.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/one")
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    private HikariDataSource dataSource;
    @RequestMapping(value = "/user")
    public String test() throws Exception {
        log.debug("TESTKU " + dataSource.getUsername());
        log.debug("TESTKU " + dataSource.getPassword());
        return DateTime.now().toLocalDate().plusYears(1).toDate().toString() + "------" + String.valueOf(testService.getDateSQL());
    }
}
