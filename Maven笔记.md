**聚合**是将单个项目分模块开发，聚合在一起并对依赖进行相关整合处理

​	分模块开发，install, compile

**依赖**具有传递性

​	主项目的Pom文件中的依赖叫直接依赖

​	依赖依赖的其他资源，叫做间接依赖

依赖的依赖叫做依赖的**直接依赖**

依赖依赖的依赖叫做依赖的**间接依赖**

**Maven依赖优先级**

同级时：配置中相同的资源不同版本，由后配置的覆盖先配置的(根据POM的位置)

路径时：依赖中出现相同资源时，层级越深，优先级越低，层级越浅，优先级越高

层级时：依赖的同层级时，配置较前覆盖配置较后的

**可选依赖** 

对外隐藏当前所依赖的资源，该隐藏的依赖无法作为间接依赖使用。(依赖的POM设定)

**排除依赖** 

主动切断依赖的资源，排除资源不需要的版本。(项目的POM设定)

**继承**是描述俩个工程之间的关系，子工程能继承父工程中的配置信息，如继承依赖关系

**Maven属性**

其他属性: cmd(mvn help:system)

自定义属性  ${自定义属性名}  ${spring.version}

内置属性    ${内置属性名}       ${basedir}   ${version}

Setting属性  ${setting.属性名}  ${setting.localRepository}   //maven配置属性

Java系统属性    ${系统属性分类.系统属性名}   ${user.home}

环境变量属性    ${env.环境变量属性名} ${env.JAVA_HOME}

**项目版本**

SNAPSHOT(快照版本，版本随着开发进度不断更新，开发中)

RELEASE(发布版本，版本对外发布，更能较为稳定，后续开发不会改变发布版本内容，已完毕)

**Execute Maven Goal**

执行： mvn install -P env_dep  //执行某一环境

​    mvn package -D skipTests //跳过测试 3种：按钮 命令 细密度

​    mvn deploy         //上传私服

**私服**:  https://help.sonatype.com/repomanager3/product-information/download

```
<!--pom是聚合文件打包方式，代表创建了管理其他工程的工程-->
<!--根据依赖关系构建，最底层依赖最先构建-->
    <modules>
        <module>../maven02-ssm</module>
        <module>../maven03-dao</module>
        <module>../maven04-pojo</module>
    </modules>

    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>  <!--每个版本mybatis对应版本不同-->
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.16</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>
    </dependencies>

<!--    定义属性-->
    <properties>
        <spring.version>5.2.10.RELEASE</spring.version>
        <junit.version>4.12</junit.version>
        <mysql.version>5.1.46</mysql.version>
<!--        继承对象才能这样设置,即子依赖中引用了父项目才行-->
        <jdbc.url>jdbc:mysql://127.0.0.1:3306/db_ssm</jdbc.url>
    </properties>
<!--    依赖管理,可选依赖;需要在子工程中添加依赖,不需要加版本号;-->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <!--*  *配置环境-->
	  <**profiles**>
 *<!--*    *开发环境**-->
\*     <**profile**>
       <**id**>env_dep</**id**>
       <**properties**>
         <**jdbc.url**>jdbc:mysql://127.0.0.1:3306/db_ssm</**jdbc.url**>
       </**properties**>
 *<!--*      *设定是否为默认的启动环境**-->
\*       <**activation**>
         <**activeByDefault**>true</**activeByDefault**>
       </**activation**>
     </**profile**>
     
 *<!--*  *配置环境-->**
	  <**profiles**>
 *<!--*    *开发环境**-->
\*     <**profile**>
       <**id**>env_dep</**id**>
       <**properties**>
         <**jdbc.url**>jdbc:mysql://127.0.0.1:3306/db_ssm</**jdbc.url**>
       </**properties**>
 *<!--*      *设定是否为默认的启动环境**-->
\*       <**activation**>
         <**activeByDefault**>true</**activeByDefault**>
       </**activation**>
     </**profile**>
     
     <build>
        <resources>
            <resource>
                <directory>${project.basedir}/src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
</project>

```

```
    <parent>
        <groupId>com.rqiang</groupId>
        <artifactId>maven01-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../maven01-parent/pom.xml</relativePath>
    </parent>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <!--        依赖domain运行-->
        <!--        <dependency>-->
        <!--            <groupId>com.rqiang</groupId>-->
        <!--            <artifactId>maven02-pojo</artifactId>-->
        <!--            <version>1.0-SNAPSHOT</version>-->
        <!--        </dependency>-->
        <!--        依赖dao运行-->
        <dependency>
            <groupId>com.rqiang</groupId>
            <artifactId>maven03-dao</artifactId>
            <version>1.0-SNAPSHOT</version>
            <!--            排除依赖，当前引用的坐标中，将当前依赖的依赖从坐标中排除出去, 将其隐藏-->
            <exclusions>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.mybatis</groupId>
                    <artifactId>mybatis</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

        <build>
        <resources>
            <resource>
                <directory>${project.basedir}/src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.12.4</version>
                <configuration>
                    <skipTests>false</skipTests>
<!--                    排除掉不参与测试的内容-->
                    <excludes>
                        <exclude>**/BookServiceTest.java</exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

 