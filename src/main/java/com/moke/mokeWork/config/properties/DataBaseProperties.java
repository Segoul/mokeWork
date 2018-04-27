package com.moke.mokeWork.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 读取druid数据库连接池参数-application.properties
 * @author Segoul
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource", ignoreInvalidFields = false)
public class DataBaseProperties {
    
	private String driverClassName;  //数据库驱动
    private String url;  //数据库URL
    private String username;  //用户名
    private String password;  //密码
    private Integer initialSize;  //初始化时建立物理连接的个数
    private Integer maxActive;  //最大连接池数量
    private Integer maxWait;  //获取连接时最大等待时间，单位毫秒
    private Integer minIdle;  //最小连接池数量
    private String validationQuery;  //用来检测连接是否有效的sql，要求是一个查询语句。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用
    private String testOnBorrow;  //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
    private String testOnReturn;  //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
    private String testWhileIdle;  //建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
    private Integer timeBetweenEvictionRunsMillis;  //(1) Destroy线程会检测连接的间隔时间(2) testWhileIdle的判断依据
    private Integer minEvictableIdleTimeMillis;  //一个连接在池中最小生存的时间
    private boolean poolPreparedStatements;  //是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
    private String filters;  //属性类型是字符串，通过别名的方式配置扩展插件,常用插件如监控统计用的filter:stat 日志用的filter:log4j 防御sql注入的filter:wall
    private Integer maxPoolPreparedStatementPerConnectionSize;  //Statement缓存大小
    private String connectionProperties;  //通过connectProperties属性来打开mergeSql合并SQL功能；慢SQL记录功能
    private boolean useGlobalDataSourceStat;  //支持配置公用监控数据
}
