<?xml version="1.0" encoding="UTF-8" ?>
<!--
 scan：当此属性设置为true时，配置文件如果发生改变，将会被重新加载，默认值为true。
 scanPeriod：设置监测配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒。当scan为true时，此属性生效。默认的时间间隔为1分钟。
 debug：当此属性设置为true时，将打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
 -->
<!-- LoggerContextListener 接口的实例能监听 logger context 上发生的事件，比如说日志级别的变化 -->true<!-- 控制台日志输出信息 --><!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符  [%d{HH:mm:ss.SSS} %logger{36} - %msg%n]-->system-log-info: %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{100} - %msg%n <!-- 将org.springframework.web包下的类日志级别设置为DEBUG，我们开发springMVC经常出现和参数类型相关的4XX错误，设置此项我们会看到更详细的错误信息  --><!-- 定义log文件的目录 --><!-- 自定义日志信息 --><!-- 支持多JVM同时操作同一个日志文件 -->true<!-- 定义打印级别-info -->INFO<!-- 输出位置 可以结合LOG_HOME用如：${LOG_HOME}/xxxxx.log -->./log/TESTLOG_all_info.log<!--
         1.ch.qos.logback.core.rolling.TimeBasedRollingPolicy"： 最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责出发滚动。
            <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            必要节点，包含文件名及“%d”转换符， “%d”可以包含一个java.text.SimpleDateFormat指定的时间格式，如：%d{yyyy-MM}。如果直接使用 %d，默认格式是 yyyy-MM-dd。
            <fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
            最多保留30天log
            <maxHistory>30</maxHistory>
            </rollingPolicy>
          2.ch.qos.logback.core.rolling.FixedWindowRollingPolicy： 根据固定窗口算法重命名文件的滚动策略。有以下子节点：
            <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
            <FileNamePattern>./log/TESTLOG_all_info.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            窗口索引最小值
            <MinIndex>1</MinIndex>
            窗口索引最大值，当用户指定的窗口过大时，会自动将窗口设置为12。
            <MaxIndex>5</MaxIndex>
            </rollingPolicy>
            必须包含“%i”例如，假设最小值和最大值分别为1和2，命名模式为 mylog%i.log,会产生归档文件mylog1.log和mylog2.log。还可以指定文件压缩选项，例如，mylog%i.log.gz 或者 没有log%i.log.zip
          3.ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy
            <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            这是活动文件的大小，默认值是10MB。　　　　　　　　　　　　　　
            <maxFileSize>5MB</maxFileSize>
    　　　　　</triggeringPolicy>
        ///////////////////////////////////////////////////////////////////////////////////////////
        例如：
　　　　　　　　<configuration>
　　　　　　　　　　<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
　　　　　　　　　　　　<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
　　　　　　　　　　　　　　<fileNamePattern>logFile.%d{yyyy-MM-dd}.log</fileNamePattern>
　　　　　　　　　　　　　　<maxHistory>30</maxHistory>
　　　　　　　　　　　　</rollingPolicy>
　　　　　　　　　　　　<encoder>
　　　　　　　　　　　　　　<pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
　　　　　　　　　　　　</encoder>
　　　　　　　　　　</appender>
　　　　　　　　　　<root level="DEBUG">
　　　　　　　　　　　　<appender-ref ref="FILE" />
　　　　　　　　　　</root>
　　　　　　　　</configuration>
　　　　　　　　上述配置表示每天生成一个日志文件，保存30天的日志文件。
　　　　　　　　<configuration>
　　　　　　　　　　<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
　　　　　　　　　　　　<file>test.log</file>
　　　　　　　　　　　　<rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
　　　　　　　　　　　　　　<fileNamePattern>tests.%i.log.zip</fileNamePattern>
　　　　　　　　　　　　　　<minIndex>1</minIndex>
　　　　　　　　　　　　　　<maxIndex>3</maxIndex>
　　　　　　　　　　　　</rollingPolicy>
　　　　　　　　　　　　<triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
　　　　　　　　　　　　　　<maxFileSize>5MB</maxFileSize>
　　　　　　　　　　　　</triggeringPolicy>
　　　　　　　　　　　　<encoder>
　　　　　　　　　　　　　　<pattern>%-4relative [%thread] %-5level %logger{35} - %msg%n</pattern>
　　　　　　　　　　　　</encoder>
　　　　　　　　　　</appender>
　　　　　　　　　　<root level="DEBUG">
　　　　　　　　　　　　<appender-ref ref="FILE" />
　　　　　　　　　　</root>
　　　　　　　　</configuration>
　　　　　　　　上述配置表示按照固定窗口模式生成日志文件，当文件大于20MB时，生成新的日志文件。窗口大小是1到3，当保存了3个归档文件后，将覆盖最早的日志。
        //////////////////////////////////////////////////////////////////////////////////////////////
         --><!-- old
        <rollingPolicy class="ch.qos.logback.core.rolling.FixedWindowRollingPolicy">
            <FileNamePattern>./log/TESTLOG_all_info.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <MinIndex>1</MinIndex>
            <MaxIndex>5</MaxIndex>
        </rollingPolicy>
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>5KB</MaxFileSize>
        </triggeringPolicy>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>%date [%thread] %-5level %logger{80} - %msg%n</Pattern>
        </layout>
        -->
        <!--日志文件输出的文件名-->./log/TESTLOG_all_info.log.%d{yyyy-MM-dd}.log<!--日志文件保留天数-->30<!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger - %msg%n<!--日志文件最大的大小-->20MBINFO./log/TESTLOG_all_test_info.log./log/TESTLOG_all_test_info.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger - %msg%nERRORACCEPTDENY./log/TESTLOG_all_error.log./log/TESTLOG_all_error.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger - %msg%nDEBUG./log/TESTLOG_all_debug.log./log/TESTLOG_all_debug.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger{80} - %msg%n./log/TESTLOG_a_debug.log./log/TESTLOG_a_debug.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger{80} - %msg%n./log/TESTLOG_b_info.log./log/TESTLOG_b_info.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger{80} - %msg%n./log/TESTLOG_c_warn.log./log/TESTLOG_c_warn.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger{80} - %msg%n./log/TESTLOG_d_error.log./log/TESTLOG_d_error.%d{yyyy-MM-dd}.%i.log155MB%date [%thread] %-5level %logger{80} - %msg%n<!-- 配置具体文件的日志输出级别 --><!-- all INFO --><!-- all ERROR --><!-- all DEBUG --><!-- console -->